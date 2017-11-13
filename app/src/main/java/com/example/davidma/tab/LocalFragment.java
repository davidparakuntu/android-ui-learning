package com.example.davidma.tab;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.davidma.tab.data.Contact;
import com.example.davidma.tab.data.DataApi;
import com.example.davidma.tab.databinding.FragmentContactBinding;

import static android.provider.ContactsContract.Contacts;
import static android.provider.ContactsContract.Data;
import static android.provider.ContactsContract.RawContacts;

import java.util.List;

/**
 * Created by davidma on 11/8/17.
 */

public class LocalFragment extends Fragment {

    public static final String FRAGEMENT_ID = "fragementID";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getArguments().getInt(FRAGEMENT_ID),null);

        ScrollView scrollView = new ScrollView(getContext());

        if(Integer.parseInt(getArguments().get("Tab#").toString())==0) {
            DataApi dataApi = new DataApi(getContext());
            Uri uri = ContactsContract.Contacts.CONTENT_URI;
            ContentResolver contentResolver = getContext().getContentResolver();
            Cursor cursor = contentResolver.query(uri, null, null, null, null);
            while (cursor.moveToNext()){
                View contactView = getLayoutInflater().inflate(R.layout.fragment_contact, null);
                FragmentContactBinding dataBinding = DataBindingUtil.bind(contactView);
                int index = cursor.getColumnIndex(Data.DISPLAY_NAME);
                String name = cursor.getString(index);
                Cursor phCursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{cursor.getString(cursor.getColumnIndex(Contacts._ID))}, null);
                Contact contact = new Contact();
                contact.setName(name);
                if(phCursor.moveToNext()) {
                    String phoneNumber = phCursor.getString(phCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    contact.setProffesion(phoneNumber);
                    phCursor.close();
                }

                Cursor emailCursor = contentResolver.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI,null,ContactsContract.CommonDataKinds.Email.CONTACT_ID+" = ?",new String[]{cursor.getString(cursor.getColumnIndex(Contacts._ID))},null);
                if(emailCursor.moveToNext()) {
                    String emailID = emailCursor.getString(emailCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.ADDRESS));
                    contact.setEmailID(emailID);
                }
                dataBinding.setContact(contact);
                ((ViewGroup)view).addView(contactView);
            }
            cursor.close();
        }
        scrollView.addView(view);
        return scrollView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    private List<Contact> contactList;
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

    }

    public static LocalFragment instance(int itemNumber){
        LocalFragment fragment = new LocalFragment();
        Bundle args = new Bundle();
        switch (itemNumber){
            case 0:{
                args.putInt(FRAGEMENT_ID,R.layout.fragment_tab0);
                break;
            }case 1:{
                args.putInt(FRAGEMENT_ID,R.layout.fragment_tab1);
                break;
            }case 2:{
                args.putInt(FRAGEMENT_ID,R.layout.fragment_tab2);
                break;
            }case 3:{
                args.putInt(FRAGEMENT_ID,R.layout.fragment_tab3);
                break;
            }
        }
        args.putInt("Tab#",itemNumber);
        fragment.setArguments(args);
        return fragment;
    }

}