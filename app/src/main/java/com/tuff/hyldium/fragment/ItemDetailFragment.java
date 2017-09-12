package com.tuff.hyldium.fragment;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.tuff.hyldium.R;
import com.tuff.hyldium.fragment_callback.ItemDetails;
import com.tuff.hyldium.model.ItemModel;
import com.tuff.hyldium.model.UserItemOrderModel;
import com.tuff.hyldium.utils.Constant;

/**
 * Created by tuffery on 15/08/17.
 */

public class ItemDetailFragment extends PriorFragment {
    public static final String ITEM = "ITEM";
    private TextView price, TVA, name, reference, priceHT, byBundle, label;
    private EditText selection;
    private ImageView itemPhoto;
    private ImageButton orderItem;
    private double bundlePart;
    private ItemModel item;

    public static Bundle extraItem(ItemModel item) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(ITEM, item);
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
        byBundle = (TextView) view.findViewById(R.id.ordered);
        label = (TextView) view.findViewById(R.id.label);
        selection = (EditText) view.findViewById(R.id.bundlePart);
        itemPhoto = (ImageView) view.findViewById(R.id.itemImage);
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

            price.setText(String.valueOf(item.price) + " €");
            priceHT.setText(String.valueOf(item.priceHT));

        }
        orderItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float typedValue = 0;
                if (!selection.getText().toString().isEmpty()) {
                    typedValue = Float.valueOf(selection.getText().toString());
                }
                UserItemOrderModel userItemOrderModel = new UserItemOrderModel(0, 0, item.id, typedValue);
                ((ItemDetails) getContext()).orderUserItem(userItemOrderModel);
            }
        });
        selection.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE) {
                    orderItem.performClick();
                }

                return false;
            }
        });
    }

    private void takePhoto() {
        //Todo
    }

    @Override
    public int getPriority() {
        return Constant.FIRSTCONTAINER_PRIORITY;
    }
}
