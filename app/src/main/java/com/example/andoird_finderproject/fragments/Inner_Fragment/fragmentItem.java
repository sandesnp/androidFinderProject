package com.example.andoird_finderproject.fragments.Inner_Fragment;

import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.provider.DocumentsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andoird_finderproject.global.global;
import com.example.andoird_finderproject.httpRequests.requestShop;
import com.example.andoird_finderproject.models.shop;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.shape.CornerTreatment;
import com.google.android.material.shape.CutCornerTreatment;
import com.google.android.material.shape.EdgeTreatment;
import com.google.android.material.shape.RelativeCornerSize;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.example.andoird_finderproject.MainActivity;
import com.example.andoird_finderproject.R;
import com.example.andoird_finderproject.models.item;
import com.google.android.material.shape.ShapePath;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class fragmentItem extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getParentFragmentManager().setFragmentResultListener("withObject_Item", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String key, @NonNull Bundle bundle) {
                // We use a String here, but any type that can be put in a Bundle is supported
                final item result = (item) bundle.getSerializable("item");
                // Do something with the result...
                if (result != null) {
                    String ItemImagePath = global.imagePath + result.getItempicture();
                    Picasso.get().load(ItemImagePath).into(ImageView_Item);
                    itemName.setText(result.getItemname());
                    itemBrand.setText(result.getItembrand());
                    itemType.setText(result.getItemtype());
                    itemDescription.setText(result.getItemdescription());
                    itemDistributor.setText(result.getShopcoordinate().getMarker());
                    String DistributorImagePath = global.imagePath + result.getShoplogo();
                    if (!result.getShoplogo().equals("")) {
                        Picasso.get().load(DistributorImagePath).into(circleImageView);
                    }

                    itemLocation.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("item", result);
                            getParentFragmentManager().setFragmentResult("item_withCoordinate", bundle);
                            dialogfragment_itemmap dialogfragment_itemmap = new dialogfragment_itemmap();
                            dialogfragment_itemmap.setStyle(DialogFragment.STYLE_NORMAL, R.style.checkoutFragmentXY);
                            dialogfragment_itemmap.show(getParentFragmentManager(), "map_itemFragment");
                        }
                    });

                }
            }
        });
    }

    ShapeableImageView ImageView_Item;
    TextView itemName, itemType, itemBrand, itemDescription, itemDistributor;
    ImageView itemLocation;
    CircleImageView circleImageView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_item, container, false);
        ImageView_Item = view.findViewById(R.id.ImageView_ItemImage);
        itemName = view.findViewById(R.id.textView_itemName);
        itemBrand = view.findViewById(R.id.textView_Brand);
        itemType = view.findViewById(R.id.textView_Type);
        itemDescription = view.findViewById(R.id.textView_itemDescription);
        itemLocation = view.findViewById(R.id.ImageView_itemLocation);
        circleImageView = view.findViewById(R.id.circleImage_itemDistributor);
        itemDistributor = view.findViewById(R.id.tv_itemDistributor);
        ShapeAppearanceModel shapeAppearanceModel = new ShapeAppearanceModel()
                .toBuilder()
                .setAllCorners(new CornerTreatment()).setAllCornerSizes(50)
                .setBottomLeftCorner(new CutCornerTreatment()).setAllCornerSizes(20)
                .setBottomRightCorner(new CutCornerTreatment()).setAllCornerSizes(20)
                .build();
        ImageView_Item.setShapeAppearanceModel(shapeAppearanceModel);
        return view;
    }

}