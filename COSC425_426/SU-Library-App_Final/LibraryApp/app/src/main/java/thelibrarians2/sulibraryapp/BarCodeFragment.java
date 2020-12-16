package thelibrarians2.sulibraryapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

public class BarCodeFragment extends Fragment {

    View view;
    ImageView iv;
    MainActivity ma;
    ActionBar toolbar;
    int startBrightness;
    LinearLayout layout;
    SharedPreferences settings;
    CardInfoFragment cardInfo;
    TextView ct, rt, nom, tv, bright;
    String barcode_data, firstName, lastName, fullName;
    View.OnClickListener ctListener, rtListener, brightListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        loadInfo();

        //name text
        nom = view.findViewById(R.id.nom);
        fullName = firstName + " " + lastName;//concatenates first & last names into a single string
        nom.setText(fullName); //sets the text for the nom text field = to fullName

        // barcode image
        iv = view.findViewById(R.id.iv);
        try {//calls a set of methods at the bottom of this class file to convert barcode number into an actual barcode
            Bitmap bitmap = encodeAsBitmap(barcode_data);
            iv.setImageBitmap(bitmap); //sets this barcode in the image field
        } catch (WriterException e) {
            e.printStackTrace();
        }

        //displays the barcode number below the actual barcode in its own text field
        tv = view.findViewById(R.id.tv);
        tv.setText(barcode_data);

        //log statement used for debugging
        Log.e("good", "end of onViewStateRestored");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e("good", "onCreateView");
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_my_card, container, false);
        ma = (MainActivity) getActivity();
        layout = view.findViewById(R.id.l);

        System.out.println(firstName + " " + lastName);

        //set of actions to be done upon clicking Add Card/Edit Card
        ctListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open a fragment to add a new card.
                // user will input values for barcode_data, firstName & lastName
                cardInfo = new CardInfoFragment(firstName, lastName, barcode_data);
                FragmentTransaction ft = ma.getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_container, cardInfo);
                Log.e("good", "barcode->mycard");
                ft.addToBackStack(null).commit();
                MainActivity.pageStack.push(MainActivity.cardPage);
            }
        } ;

        //set of actions to be done upon clicking Remove
        rtListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rt.getVisibility() == View.VISIBLE) {
                    removeAlert();
                }
            }
        } ;

        try {
            startBrightness = Settings.System.getInt(ma.getApplicationContext().getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        brightListener = new View.OnClickListener() { //toggles brightness
            @Override
            public void onClick(View arg0) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (!Settings.System.canWrite(ma.getApplicationContext())) {
                        Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS, Uri.parse("package:" + ma.getPackageName()));
                        startActivityForResult(intent, 200);

                    }
                }
                int curBrightnessValue= 0;
                try {
                    curBrightnessValue = Settings.System.getInt(ma.getApplicationContext().getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
                } catch (Settings.SettingNotFoundException e) {
                    e.printStackTrace();
                }
                if (startBrightness != 255 && curBrightnessValue != 255){
                    android.provider.Settings.System.putInt(ma.getApplicationContext().getContentResolver(), android.provider.Settings.System.SCREEN_BRIGHTNESS, 255);
                }
                else if (startBrightness != 255) {
                    android.provider.Settings.System.putInt(ma.getApplicationContext().getContentResolver(), android.provider.Settings.System.SCREEN_BRIGHTNESS, startBrightness);
                }
            }
        } ;


        //sets buttons and button listeners
        ct = view.findViewById(R.id.addCard);
        ct.setOnClickListener(ctListener);
        rt = view.findViewById(R.id.Remove);
        rt.setOnClickListener(rtListener);
        bright = view.findViewById(R.id.Brightness);
        bright.setOnClickListener(brightListener);

        //name text
        nom = view.findViewById(R.id.nom);
        fullName = firstName + " " + lastName;//concat first & last names
        nom.setText(fullName);

        // barcode image
        iv = view.findViewById(R.id.iv);
        try {
            Bitmap bitmap = encodeAsBitmap(barcode_data);
            iv.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }

        //barcode text
        tv = view.findViewById(R.id.tv);
        tv.setText(barcode_data);

        //rt.setVisibility(View.GONE);//make remove button visible
        nom.setText(null);
        tv.setText(null);
        iv.setImageBitmap(null);
        //change toolbar title
        toolbar = ma.getSupportActionBar();
        assert toolbar != null;
        toolbar.setTitle(getResources().getString(R.string.card));

        Log.e("good", "before return view");
        return view;
    }

    // Loads info from CardInfoFragment
    public void loadInfo() {
        Log.e("good", "onViewStateRestored");
        Activity activity = getActivity();

        //If Statement checks if keys for first name, last name, and barcode number in SharedPreference
        //are not null before making their contents visible
        assert activity != null;
        if ((activity.getSharedPreferences(CardInfoFragment.FIRST_NAME, 0).getString(CardInfoFragment.FIRST_NAME, null) != null) &&
                (activity.getSharedPreferences(CardInfoFragment.LAST_NAME, 0).getString(CardInfoFragment.LAST_NAME, null) != null &&
                        (activity.getSharedPreferences(CardInfoFragment.BAR_CODE, 0).getString(CardInfoFragment.BAR_CODE, null) != null))) {
            //log statement used for debugging
            Log.e("good", "get barcode");
            //grabs name and bar code data from shared preferences
            settings = activity.getSharedPreferences(CardInfoFragment.FIRST_NAME, 0);
            firstName = settings.getString(CardInfoFragment.FIRST_NAME, null);
            settings = activity.getSharedPreferences(CardInfoFragment.LAST_NAME, 0);
            lastName = settings.getString(CardInfoFragment.LAST_NAME, null);
            settings = activity.getSharedPreferences(CardInfoFragment.BAR_CODE, 0);
            barcode_data = settings.getString(CardInfoFragment.BAR_CODE, null);
            ct.setText(R.string.edit);//change text of add card
            bright.setVisibility(View.VISIBLE); //make brightness button visible
            layout.setVisibility(View.VISIBLE);//make layout containing barcode visible
            rt.setVisibility(View.VISIBLE);//make remove button visible
            nom.setVisibility(View.VISIBLE);//make name text box visible
            tv.setVisibility(View.VISIBLE);//make barcode number text box visible
            iv.setVisibility(View.VISIBLE);//make barcode visible
        }
    }

    // Removes the current Gull Card saved on the app
    public void removeCard() {
        //clears name and barcode from shared preferences
        Objects.requireNonNull(getContext()).getSharedPreferences(CardInfoFragment.FIRST_NAME, 0).edit().clear().apply();
        getContext().getSharedPreferences(CardInfoFragment.LAST_NAME, 0).edit().clear().apply();
        getContext().getSharedPreferences(CardInfoFragment.BAR_CODE, 0).edit().clear().apply();
        iv.setImageBitmap(null);//sets image to null
        Log.e("good", "remove card");
        ct.setText(R.string.add_card);//change text of add card
        rt.setVisibility(View.GONE);//make remove button visible
        bright.setVisibility(View.INVISIBLE);//makes brightness button invisible
        nom.setVisibility(View.INVISIBLE);//makes text fields invisible
        tv.setVisibility(View.INVISIBLE);
        iv.setVisibility(View.INVISIBLE);
        layout.setVisibility(View.INVISIBLE);//make layout containing barcode invisible

        firstName = "";
        lastName = "";
        barcode_data = "";
    }

    // Pop-up to verify the removal of the Gull Card
    public void removeAlert() {
        CustomAlertDialogBuilder dialog = new CustomAlertDialogBuilder(getActivity());

        dialog.setTitle(R.string.bar_popup_title);
        dialog.setMessage(R.string.bar_popup_desc);
        String cancel = getString(R.string.cancel);
        final String yes = getString(R.string.bar_popup_yes);

        dialog.setButton(DialogInterface.BUTTON_POSITIVE, yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                removeCard();
            }
        });
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    // Builds the bar code for the based on the users input in CardInfoFragment
    Bitmap encodeAsBitmap(String contents) throws WriterException {
        final int WHITE = 0xFFFFFFFF;
        final int BLACK = 0xFF000000;

        if (contents == null) {
            return null;
        }
        Map<EncodeHintType, Object> hints = null;
        String encoding = guessAppropriateEncoding(contents);
        if (encoding != null) {
            hints = new EnumMap<>(EncodeHintType.class);
            hints.put(EncodeHintType.CHARACTER_SET, encoding);
        }
        MultiFormatWriter writer = new MultiFormatWriter();
        BitMatrix result;
        try {
            result = writer.encode(contents, BarcodeFormat.CODE_128, 600, 300, hints);
        } catch (IllegalArgumentException iae) {
            // Unsupported format
            return null;
        }
        int width = result.getWidth();
        int height = result.getHeight();
        int[] pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            int offset = y * width;
            for (int x = 0; x < width; x++) {
                pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
            }
        }

        Bitmap bitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }
    private static String guessAppropriateEncoding(CharSequence contents) {
        // Very crude at the moment
        for (int i = 0; i < contents.length(); i++) {
            if (contents.charAt(i) > 0xFF) {
                return "UTF-8";
            }
        }
        return null;
    }
}
