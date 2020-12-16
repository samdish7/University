package thelibrarians2.sulibraryapp;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Paint;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.core.content.res.ResourcesCompat;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ResearchHelpFragment extends Fragment implements AdapterView.OnItemClickListener {

    ListView listView;
    ListviewX lix;
    ArrayList<ListItem> listItems;
    TextView loading_msg;
    Activity activity;
    ActionBar toolbar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_research_help, container, false);

        Resources r = getResources(); // Resources object

        //creating custom listview
		lix = new ListviewX(getActivity()); // ListView object
        listItems = new ArrayList<ListItem>(); // ArrayList of list items
        activity = getActivity(); // MainActivity object

        //show loading message to the user
        loading_msg = (TextView) view.findViewById(R.id.research_list_loading); // Gets loading object
        loading_msg.setVisibility(View.VISIBLE); // Starts loading messages

        listView = (ListView) view.findViewById(R.id.listView); //need to be able to access an xml element with java so that you can modify it dynamically
        listView.setVisibility(View.INVISIBLE); // Set invisible until set up

		ListItem0 li0 = new ListItem0(activity, r.getString(R.string.lib_basic)); // New header
        listItems.add(styleLikeHeader(li0)); // Add to list

        //manually add first items into the listview (Library Basics items)
		listItems.add(new ListItem1(activity, R.drawable.research, r.getString(R.string.topic)));
		listItems.add(new ListItem1(activity, R.drawable.keyword, r.getString(R.string.keywords)));
		listItems.add(new ListItem1(activity, R.drawable.book, r.getString(R.string.ebook)));
		listItems.add(new ListItem1(activity, R.drawable.articles, r.getString(R.string.article)));
		listItems.add(new ListItem1(activity, R.drawable.evaluate, r.getString(R.string.eval_info)));
		listItems.add(new ListItem1(activity, R.drawable.bib, r.getString(R.string.bib)));

		li0 = new ListItem0(activity, r.getString(R.string.res_subj)); // New header
        listItems.add(styleLikeHeader(li0)); // Add to list

        //manually adding all subject information into the listview
        listItems.add(new ListItem1(activity, R.drawable.accounting, r.getString(R.string.legal)));
        listItems.add(new ListItem1(activity, R.drawable.art, r.getString(R.string.art)));
        listItems.add(new ListItem1(activity, R.drawable.biology, r.getString(R.string.bio)));
		listItems.add(new ListItem1(activity, R.drawable.business, r.getString(R.string.bus)));
        listItems.add(new ListItem1(activity, R.drawable.eli, r.getString(R.string.cenedu)));
        listItems.add(new ListItem1(activity, R.drawable.chemistry, r.getString(R.string.chem)));
        listItems.add(new ListItem1(activity, R.drawable.comm, r.getString(R.string.comm)));
        listItems.add(new ListItem1(activity, R.drawable.compsci, r.getString(R.string.comp)));
        listItems.add(new ListItem1(activity, R.drawable.cadr, r.getString(R.string.cadr)));
        listItems.add(new ListItem1(activity, R.drawable.dance, r.getString(R.string.dance)));
        // TODO New pic
        listItems.add(new ListItem1(activity, R.drawable.education, r.getString(R.string.elem)));
        listItems.add(new ListItem1(activity, R.drawable.economy, r.getString(R.string.econ)));
        listItems.add(new ListItem1(activity, R.drawable.education, r.getString(R.string.edule)));
        listItems.add(new ListItem1(activity, R.drawable.engineering, r.getString(R.string.engin)));
        listItems.add(new ListItem1(activity, R.drawable.english, r.getString(R.string.engl)));
        listItems.add(new ListItem1(activity, R.drawable.environ, r.getString(R.string.env)));
        listItems.add(new ListItem1(activity, R.drawable.geog, r.getString(R.string.geog)));
        listItems.add(new ListItem1(activity, R.drawable.govt, r.getString(R.string.govt)));
        listItems.add(new ListItem1(activity, R.drawable.hss, r.getString(R.string.hss)));
        listItems.add(new ListItem1(activity, R.drawable.history, r.getString(R.string.hist)));
        // TODO: Honors College Pic
       listItems.add(new ListItem1(activity, R.drawable.education, r.getString(R.string.hon)));
        listItems.add(new ListItem1(activity, R.drawable.ids, r.getString(R.string.info)));
        listItems.add(new ListItem1(activity, R.drawable.inter, r.getString(R.string.inter)));
        // TODO: Literacy Studies Pic
        listItems.add(new ListItem1(activity, R.drawable.english, r.getString(R.string.litstud)));
        listItems.add(new ListItem1(activity, R.drawable.mgmt, r.getString(R.string.mgmt)));
        listItems.add(new ListItem1(activity, R.drawable.math, r.getString(R.string.math)));
        listItems.add(new ListItem1(activity, R.drawable.mls, r.getString(R.string.med)));
        listItems.add(new ListItem1(activity, R.drawable.mil, r.getString(R.string.mil)));
        listItems.add(new ListItem1(activity, R.drawable.modlang, r.getString(R.string.modl)));
        listItems.add(new ListItem1(activity, R.drawable.music, r.getString(R.string.music)));
        listItems.add(new ListItem1(activity, R.drawable.nursing, r.getString(R.string.nurse)));
        listItems.add(new ListItem1(activity, R.drawable.philosophy, r.getString(R.string.phil)));
        listItems.add(new ListItem1(activity, R.drawable.physed, r.getString(R.string.physed)));
        listItems.add(new ListItem1(activity, R.drawable.physics, r.getString(R.string.phys)));
        listItems.add(new ListItem1(activity, R.drawable.polisci, r.getString(R.string.polit)));
        listItems.add(new ListItem1(activity, R.drawable.psychology, r.getString(R.string.psych)));
        listItems.add(new ListItem1(activity, R.drawable.resp, r.getString(R.string.resp)));
        // TODO: New pic
        listItems.add(new ListItem1(activity, R.drawable.education, r.getString(R.string.sec)));
        listItems.add(new ListItem1(activity, R.drawable.socialwork, r.getString(R.string.soc)));
        listItems.add(new ListItem1(activity, R.drawable.sociology, r.getString(R.string.socio)));
        listItems.add(new ListItem1(activity, R.drawable.theatre, r.getString(R.string.thea)));
        lix.populate(listItems);
        listView.setAdapter(lix);
        loading_msg.setVisibility(View.INVISIBLE);
        listView.setVisibility(View.VISIBLE);
        listView.setOnItemClickListener(this);
        toolbar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        toolbar.setTitle(getResources().getString(R.string.research));
        return view;
    }

    //when we click on the item to take you to the desired page
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //CAUTION: section headers count as positions
        //i.e. position 0 is section header 1
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        webViewFragment webView;

        switch(position) {
            //Select a Research Topic URL
            case 1:
                webView = new webViewFragment("https://libraryguides.salisbury.edu/howtodolibrary/topic", "Research Topic");
                fragmentTransaction.replace(R.id.content_container, webView);
                break;
            //Develop Keywords URL
            case 2:
                webView = new webViewFragment("https://libraryguides.salisbury.edu/howtodolibrary/keywords", "Keywords");
                fragmentTransaction.replace(R.id.content_container, webView);
                break;
            //Find Books & eBooks URL
            case 3:
                webView = new webViewFragment("https://libraryguides.salisbury.edu/howtodolibrary/findbooks", "Find Books");
                fragmentTransaction.replace(R.id.content_container, webView);
                break;
            //Find Articles URL
            case 4:
                webView = new webViewFragment("https://libraryguides.salisbury.edu/howtodolibrary/findarticles", "Find Articles");
                fragmentTransaction.replace(R.id.content_container, webView);
                break;
            //Critically Evaluate Information URL
            case 5:
                webView = new webViewFragment("https://libraryguides.salisbury.edu/howtodolibrary/criticallyevaluate", "Evaluate Information");
                fragmentTransaction.replace(R.id.content_container, webView);
                break;
            //Create an Annotated Bibliography URL
            case 6:
                webView = new webViewFragment("https://libraryguides.salisbury.edu/c.php?g=327806&p=3146470", "Annotated Bibliography");
                fragmentTransaction.replace(R.id.content_container, webView);
                break;
            // Case 7 is the field header

            //Accounting & Legal Studies
            case 8:
                //add to every case statement for the resources by subject
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(0));
                break;
            // Art & Art History
            case 9:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(1));
                break;
            //Biology
            case 10:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(2));
                break;
            // Business
            case 11:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(3));
                break;
            // Center for International Education
            case 12:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(4));
                break;
            // Chemistry
            case 13:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(5));
                break;
            // Communication Arts
            case 14:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(6));
                break;
            // Computer Science
            case 15:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(7));
                break;
            // Conflict Analysis & Dispute Resolution
            case 16:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(8));
                break;
            // Dance
            case 17:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(9));
                break;
            // Early and Elementary Education
            case 18:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(10));
                break;
            //Economics & Finance
            case 19:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(11));
                break;
            // Education Leadership
            case 20:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(12));
                break;
            // Engineering
            case 21:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(13));
                break;
            // English
            case 22:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(14));
                break;
            // Environmental Studies
            case 23:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(15));
                break;
            // Geography & Geosciences
            case 24:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(16));
                break;
            // Government Information
            case 25:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(17));
                break;
            // Health Sciences
            case 26:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(18));
                break;
            // History
            case 27:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(19));
                break;
            // Honors College
            case 28:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(20));
                break;
            // Information & Decision Sciences
            case 29:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(21));
                break;
            // Interdisciplinary Studies
            case 30:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(22));
                break;
            // Literacy Studies
            case 31:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(23));
                break;
            // Management & Marketing
            case 32:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(24));
                break;
            // Mathematics
            case 33:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(25));
                break;
            // Medical Laboratory Science
            case 34:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(26));
                break;
            // Military Science
            case 35:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(27));
                break;
            // Modern Languages
            case 36:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(28));
                break;
            // Music
            case 37:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(29));
                break;
            // Nursing
            case 38:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(30));
                break;
            // Philosophy
            case 39:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(31));
                break;
            // Physical Education
            case 40:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(32));
                break;
            // Physics
            case 41:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(33));
                break;
            // Political Science
            case 42:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(34));
                break;
            // Psychology
            case 43:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(35));
                break;
            // Respiratory Therapy
            case 44:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(36));
                break;
            // Secondary Education
            case 45:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(37));
                break;
            // Social Work
            case 46:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(38));
                break;
            // Sociology
            case 47:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(39));
                break;
            // Theatre
            case 48:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(40));
                break;
        }
        //add to back stack -> create the sequence of pages visited to come back to later if necessary
        fragmentTransaction.addToBackStack(null).commit();

        MainActivity.pageStack.push(MainActivity.researchPage);
    }

    private ListItem0 styleLikeHeader(ListItem0 li){
        li.getTextView().setTextAppearance(getActivity(), R.style.listHeader); // Looks like a header
        li.getTextView().setPaintFlags(li.getTextView().getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG); // Underlines
        li.getLayout().setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.listHeader, null)); // Sets background color to the standard
        return li;
    }
}