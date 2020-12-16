package thelibrarians2.sulibraryapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ChatWebViewFragment extends Fragment {
    View web;
    String urlstr;//string containing url
    private WebView webview = null;
    DrawerToggleListener toggleListener;
    TextView loadingmsg = null;

    public ChatWebViewFragment(){}

    public ChatWebViewFragment(String urlstr){
        this.urlstr=urlstr;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(savedInstanceState != null){
            webview.restoreState(savedInstanceState);
        }
        setRetainInstance(false);
        web = inflater.inflate(R.layout.web_view, container, false);
        RelativeLayout layout = (RelativeLayout) web.findViewById(R.id.weblayout);
        loadingmsg = (TextView) layout.findViewById(R.id.loadingmsgwebview);
        loadingmsg.setVisibility(View.VISIBLE);
        if (webview == null) {
            webview = new WebView(getActivity());
            webview.loadUrl(urlstr);
        }
        else{
            webview.setVisibility(View.INVISIBLE);
        }
        layout.removeView(webview);
        loadingmsg.setVisibility(View.VISIBLE);
        WebSettings webSettings = webview.getSettings();//set permissions
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setLoadsImagesAutomatically(true);
        webview.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                //hide loading image
                loadingmsg.setVisibility(View.INVISIBLE);
                view.setVisibility(View.VISIBLE);
            }
        });
        layout.addView(webview, layout.getLayoutParams());
        toggleListener = (DrawerToggleListener) getActivity();
        toggleListener.toggleDrawer(false);
        webview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        return web;
    }

    @Override
    public void onPause() {
        setRetainInstance(true);
        if (getRetainInstance() && webview.getParent() instanceof ViewGroup) {
            ((ViewGroup)webview.getParent()).removeView(webview);
        }
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        setRetainInstance(true);
        if (getRetainInstance() && webview.getParent() instanceof ViewGroup) {
            ((ViewGroup)webview.getParent()).removeView(webview);
        }
        super.onDestroyView();
        toggleListener.toggleDrawer(true);
    }
}
