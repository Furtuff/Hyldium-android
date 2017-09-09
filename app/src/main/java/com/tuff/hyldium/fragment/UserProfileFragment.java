package com.tuff.hyldium.fragment;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tuff.hyldium.R;
import com.tuff.hyldium.fragment_callback.UserProfile;
import com.tuff.hyldium.model.UserModel;
import com.tuff.hyldium.utils.Constant;

/**
 * Created by tuffery on 22/07/17.
 */

public class UserProfileFragment extends PriorFragment {
    public static final String USER_DETAILS = "USER_DETAILS";
    private TextView firstName, lastName;
    private Button changePassword, disconnect;
    private ImageView photo;

    public static Bundle extraItem(UserModel userModel) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(USER_DETAILS, userModel);
        return bundle;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View userProfileView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_user_profile, container, false);
        firstName = (TextView) userProfileView.findViewById(R.id.firstName);
        lastName = (TextView) userProfileView.findViewById(R.id.lastName);
        changePassword = (Button) userProfileView.findViewById(R.id.changePassword);
        disconnect = (Button) userProfileView.findViewById(R.id.disconnect);
        photo = (ImageView) userProfileView.findViewById(R.id.userPhoto);
        return userProfileView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        UserModel userModel = (UserModel) getArguments().getSerializable(USER_DETAILS);

        if (userModel != null) {
            firstName.setText(userModel.firstName);
            lastName.setText(userModel.lastName);

            if (userModel.photo != null) {
                photo.setImageBitmap(BitmapFactory.decodeByteArray(userModel.photo, 0, userModel.photo.length));
            }
            photo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Todo take shot
                }
            });
            disconnect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((UserProfile) view.getContext()).disconnect();
                }
            });
        }

    }

    @Override
    public int getPriority() {
        return Constant.FIRSTCONTAINER_PRIORITY;
    }
}
