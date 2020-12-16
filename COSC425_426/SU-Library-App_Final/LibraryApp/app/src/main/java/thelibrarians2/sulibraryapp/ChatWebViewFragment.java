package thelibrarians2.sulibraryapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.Objects;

/**
 * Revised by Declan Sheehan, Sam Disharoon, & Jack Stoetzel in 2020
 * Description: Implementation of the chatview fragment, used to chat with various library employees
 */

public class ChatWebViewFragment extends Fragment {
    // Define class variables.
    private WebView webview = null;
    ProgressBar loading = null;
    String urlstr;
    View web;

    // Create an empty default constructor.
    public ChatWebViewFragment(){}

    // Create another constructor for passing in urls.
    public ChatWebViewFragment(String urlstr){
        this.urlstr=urlstr;
    }

    @Override
    @SuppressLint("SetJavaScriptEnabled")
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setRetainInstance(true);
        web = inflater.inflate(R.layout.web_view, container, false);

        // Restore instance state if not null.
        if(savedInstanceState != null)
            webview.restoreState(savedInstanceState);

        RelativeLayout layout = web.findViewById(R.id.weblayout);
        loading = layout.findViewById(R.id.loading_panel);
        loading.setVisibility(View.VISIBLE);
        if (webview == null) {
            webview = new WebView(Objects.requireNonNull(getActivity()));
            webview.loadUrl(urlstr);
        }
        layout.removeView(webview);
        loading.setVisibility(View.VISIBLE);

        // Get the current web settings, and change its options respectively.
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setJavaScriptEnabled(true);

        // Hide the loading message when the page is finished loading.
        webview.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                loading.setVisibility(View.INVISIBLE);
                view.setVisibility(View.VISIBLE);
            }
        });

        layout.addView(webview, layout.getLayoutParams());
        webview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        return web;
    }

    @Override
    public void onPause() {
        setRetainInstance(true);
        super.onPause();
    }

    @Override
    public void onResume() {
        setRetainInstance(true);
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        setRetainInstance(true);
        if (getRetainInstance() && webview.getParent() instanceof ViewGroup) {
            ((ViewGroup)webview.getParent()).removeView(webview);
        }
        super.onDestroyView();
    }
}
