package com.tuff.hyldium.fragment;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.tuff.hyldium.R;
import com.tuff.hyldium.fragment_callback.ItemDetails;
import com.tuff.hyldium.model.ItemModel;
import com.tuff.hyldium.model.UserItemOrderModel;

import java.io.Serializable;

/**
 * Created by tuffery on 15/08/17.
 */

public class ItemDetailFragment extends Fragment {
    public static final String ITEM = "ITEM";
    BoomMenuButton bmb;
    private TextView price, TVA, name, reference, priceHT, byBundle, label, barCode;
    private EditText selection;
    private ImageView itemPhoto;
    private ImageButton orderItem;
    private double bundlePart;
    private ItemModel item;

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
        bmb = (BoomMenuButton) view.findViewById(R.id.moreOptions);
        orderItem = (ImageButton) view.findViewById(R.id.orderItem);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            item = (ItemModel) bundle.getSerializable(ITEM);
            if (item.photo != null) {
                itemPhoto.setImageBitmap(BitmapFactory.decodeByteArray(item.photo, 0, item.photo.length));
                itemPhoto.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        takePhoto();
                        return false;
                    }
                });
            } else {
                itemPhoto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        takePhoto();
                    }
                });
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
        TextOutsideCircleButton.Builder builder = new TextOutsideCircleButton.Builder()
                .normalImageRes(android.R.drawable.ic_menu_edit)
                .normalText("edit");
        bmb.addBuilder(builder);
        orderItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserItemOrderModel userItemOrderModel = new UserItemOrderModel(0, 0, item.id, Float.valueOf(selection.getText().toString()));
                ((ItemDetails) getContext()).orderUserItem(userItemOrderModel);
            }
        });
    }

    private void takePhoto() {
        //Todo
    }
}
