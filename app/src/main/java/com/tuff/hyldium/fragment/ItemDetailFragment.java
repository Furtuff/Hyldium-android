package com.tuff.hyldium.fragment;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.tuff.hyldium.R;
import com.tuff.hyldium.model.ItemModel;

import java.io.Serializable;

/**
 * Created by tuffery on 15/08/17.
 */

public class ItemDetailFragment extends Fragment {
    public static final String ITEM = "ITEM";
    private TextView price, TVA, name, reference, priceHT, byBundle, label, barCode;
    private EditText selection;
    private ImageView itemPhoto;
    private double bundlePart;

    public static Bundle extraItem(ItemModel item) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(ITEM, (Serializable) item);
        return bundle;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_details, container, false);
        price = (TextView) view.findViewById(R.id.price);
        TVA = (TextView) view.findViewById(R.id.TVA);
        name = (TextView) view.findViewById(R.id.name);
        reference = (TextView) view.findViewById(R.id.reference);
        priceHT = (TextView) view.findViewById(R.id.priceHT);
        byBundle = (TextView) view.findViewById(R.id.byBundle);
        label = (TextView) view.findViewById(R.id.label);
        barCode = (TextView) view.findViewById(R.id.barcode);
        selection = (EditText) view.findViewById(R.id.bundlePart);
        itemPhoto = (ImageView) view.findViewById(R.id.itemImage);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            ItemModel item = (ItemModel) bundle.getSerializable(ITEM);
            if (item.photo != null) {
                itemPhoto.setImageBitmap(BitmapFactory.decodeByteArray(item.photo, 0, item.photo.length));
            }

            name.setText(item.name);
            reference.setText(item.reference);
            TVA.setText(String.valueOf(item.TVA));
            byBundle.setText(String.valueOf(item.byBundle));
            if (item.label != null) {
                label.setText(item.label);
            }
            if (item.barCode != null) {
                barCode.setText(String.valueOf(item.barCode));
            }
            price.setText(String.valueOf(item.price) + " €");
            priceHT.setText(String.valueOf(item.priceHT));

        }

    }
}
