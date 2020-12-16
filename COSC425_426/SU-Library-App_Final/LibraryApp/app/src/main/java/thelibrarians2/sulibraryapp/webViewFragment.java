package thelibrarians2.sulibraryapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.Objects;



/**
 * Created by Xopher on 11/7/2016.
 *  revamped by Sam Disharoon, Jack Stoetzel, Declan Sheehan & Jordan Welch in 2020.
 *  Implementation of creating webviews
 *  - Opens up a webpage in app
 *  - used in:
 *  - Library News
 *  - Chat
 *  - Research Help
 *  - Helpful Links
 *  - Building Maps
 *  - Search for Resources
 *  - SU Maker Lab
 *  - Contact Information
 */

public class webViewFragment extends Fragment {

    // Define class variables.
    static WebView webview = null;
    static String toolbar_name = null;
    ProgressBar loading = null;
    static String urlstr = null;
    ActionBar toolbar;
    View web;

    // Constructor
    public webViewFragment(String urlstr, String toolbar_name) {
        webViewFragment.urlstr = urlstr;
        webViewFragment.toolbar_name = toolbar_name;
    }

    // Creates the view:
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setRetainInstance(false);
        web = inflater.inflate(R.layout.web_view, container, false);

        // Sets the toolbar title to the passed in webview name.
        toolbar = ((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar();
        assert toolbar != null;
        toolbar.setTitle(toolbar_name);

        RelativeLayout layout = web.findViewById(R.id.weblayout);
        loading = layout.findViewById(R.id.loading_panel);
        loading.setVisibility(View.VISIBLE);

        // Handle issues with the webview.
        if (webview == null) {
            webview = new WebView(Objects.requireNonNull(getActivity()));
            webview.loadUrl(urlstr);
        } else if (Objects.requireNonNull(webview.getUrl()).compareTo(urlstr) != 0){
            webview.loadUrl(urlstr);
        } else {
            webview.setVisibility(View.INVISIBLE);
        }
        webview.setVisibility(View.INVISIBLE);
        layout.removeView(webview);

        // Edit web settings.
        WebSettings webSettings = webview.getSettings();// Set permissions
        webSettings.setJavaScriptEnabled(true); // Needed for Duo 2FA to work.
        webSettings.setDomStorageEnabled(true); // Fixes many links that result in a blank screen.
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setLoadsImagesAutomatically(true);
        webview.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                loading.setVisibility(View.INVISIBLE);
                view.setVisibility(View.VISIBLE);
            }
        });
        layout.addView(webview, layout.getLayoutParams());
        webview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);

        // Return the view to be inflated once the fragment is called.
        return web;
    }

    // Returns the webview
    public static WebView getWebView(){
        return webview;
    }

    // Redefine onPause.
    @Override
    public void onPause() {
        setRetainInstance(true);
        super.onPause();
    }

    // Redefine onDestroyView.
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        webview.destroy();
        webview = null;
    }
}
