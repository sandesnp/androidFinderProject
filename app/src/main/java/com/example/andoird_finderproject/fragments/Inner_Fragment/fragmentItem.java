package com.example.andoird_finderproject.fragments.Inner_Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.shape.CornerTreatment;
import com.google.android.material.shape.CutCornerTreatment;
import com.google.android.material.shape.EdgeTreatment;
import com.google.android.material.shape.RelativeCornerSize;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.example.andoird_finderproject.MainActivity;
import com.example.andoird_finderproject.R;
import com.example.andoird_finderproject.models.item;
import com.google.android.material.shape.ShapePath;

public class fragmentItem extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getParentFragmentManager().setFragmentResultListener("withObject_Item", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String key, @NonNull Bundle bundle) {
                // We use a String here, but any type that can be put in a Bundle is supported
                item result = (item) bundle.getSerializable("item");
                // Do something with the result...
                if (result != null) {
                    Toast.makeText(getContext(), result.getItemname(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    MaterialButton btn_check;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_item, container, false);
        btn_check=view.findViewById(R.id.check_button);
        ShapeAppearanceModel shapeAppearanceModel = new ShapeAppearanceModel()
                .toBuilder()
                .setTopRightCorner(new CutCornerTreatment()).setAllCornerSizes(50)
                .build();
        btn_check.setShapeAppearanceModel(shapeAppearanceModel);

        return view;
    }
}