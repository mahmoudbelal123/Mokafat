package quadrant.mokafat.points.view.single_partner_details.single_partner_tabs.tab2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import javax.inject.Inject;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.dagger.DaggerApplication;
import quadrant.mokafat.points.helper.GlideApp;
import quadrant.mokafat.points.models.objects.get_branches.DataObject;
import quadrant.mokafat.points.models.objects.get_branches.get_branches_by_id.BranchesList;
import quadrant.mokafat.points.models.objects.get_branches.get_branches_by_id.GetBranchesByBranchIdResponse;
import quadrant.mokafat.points.view.get_location_map.CustomInfoWindowGoogleMap;
import quadrant.mokafat.points.view.get_location_map.InfoWindowData;

/* JADX INFO: loaded from: classes.dex */
public class SingleAllBranchFragment extends Fragment implements OnMapReadyCallback, View.OnClickListener, VendorBranchesView {
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    String address;
    List<Address> addresses;
    ExpandableListView expListView;
    Geocoder geocoder;
    private ImageView imageViewVendorLogo;
    ExpandableBranchesListAdapter listAdapter;
    HashMap<String, List<BranchesList>> listDataChild;
    List<String> listDataHeader;
    Marker mCurrLocationMarker;
    FusedLocationProviderClient mFusedLocationClient;
    GoogleMap mGoogleMap;
    Location mLastLocation;
    LocationCallback mLocationCallback = new LocationCallback() { // from class: quadrant.mokafat.points.view.single_partner_details.single_partner_tabs.tab2.SingleAllBranchFragment.1
        @Override // com.google.android.gms.location.LocationCallback
        public void onLocationResult(LocationResult locationResult) {
            try {
                List<Location> locationList = locationResult.getLocations();
                Location location = locationList.get(locationList.size() - 1);
                SingleAllBranchFragment.this.geocoder = new Geocoder(SingleAllBranchFragment.this.getActivity(), Locale.getDefault());
                double lat = location.getLatitude();
                double lon = location.getLongitude();
                SingleAllBranchFragment.this.addresses = SingleAllBranchFragment.this.geocoder.getFromLocation(lat, lon, 1);
                SingleAllBranchFragment.this.address = SingleAllBranchFragment.this.addresses.get(0).getAddressLine(0);
                SingleAllBranchFragment.this.addresses.get(0).getLocality();
                SingleAllBranchFragment.this.addresses.get(0).getAdminArea();
                SingleAllBranchFragment.this.addresses.get(0).getCountryName();
                SingleAllBranchFragment.this.addresses.get(0).getPostalCode();
                SingleAllBranchFragment.this.addresses.get(0).getFeatureName();
                if (locationList.size() > 0) {
                    SingleAllBranchFragment.this.mLastLocation = location;
                    location.getLatitude();
                    location.getLongitude();
                    LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(latLng);
                    markerOptions.title(SingleAllBranchFragment.this.address);
                    markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.my_location_icon));
                    new InfoWindowData();
                    SingleAllBranchFragment.this.mCurrLocationMarker = SingleAllBranchFragment.this.mGoogleMap.addMarker(markerOptions);
                    SingleAllBranchFragment.this.mGoogleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() { // from class: quadrant.mokafat.points.view.single_partner_details.single_partner_tabs.tab2.SingleAllBranchFragment.1.1
                        @Override // com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
                        public boolean onMarkerClick(Marker marker) {
                            SingleAllBranchFragment.this.mCurrLocationMarker.showInfoWindow();
                            return false;
                        }
                    });
                    CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getLongitude()));
                    CameraUpdate zoom = CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 10.0f);
                    SingleAllBranchFragment.this.mGoogleMap.animateCamera(zoom);
                    SingleAllBranchFragment.this.vendorBranchesPresenter.getBranchesOfVendor();
                    SingleAllBranchFragment.this.vendorBranchesPresenter.getBranchesCityOfVendor();
                }
            } catch (Exception e) {
            }
        }
    };
    LocationRequest mLocationRequest;
    SupportMapFragment mapFrag;
    Marker marker;

    @Inject
    VendorBranchesPresenter vendorBranchesPresenter;
    private View view;

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_single_all_branch, container, false);
        this.imageViewVendorLogo = (ImageView) this.view.findViewById(R.id.imageView5_vendor_logo_branches);
        this.expListView = (ExpandableListView) this.view.findViewById(R.id.lvExp);
        Display newDisplay = getActivity().getWindowManager().getDefaultDisplay();
        int width = newDisplay.getWidth();
        this.expListView.setIndicatorBounds(width - 80, width);
        this.mFusedLocationClient = LocationServices.getFusedLocationProviderClient((Activity) getActivity());
        this.mapFrag = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        this.mapFrag.getMapAsync(this);
        ((DaggerApplication) getActivity().getApplication()).getAppComponent().inject(this);
        this.vendorBranchesPresenter.onAttach((VendorBranchesView) this);
        return this.view;
    }

    @Override // android.support.v4.app.Fragment
    public void onPause() {
        super.onPause();
        if (this.mFusedLocationClient != null) {
            this.mFusedLocationClient.removeLocationUpdates(this.mLocationCallback);
        }
    }

    @Override // com.google.android.gms.maps.OnMapReadyCallback
    public void onMapReady(GoogleMap googleMap) {
        this.mGoogleMap = googleMap;
        this.mGoogleMap.setMapType(1);
        this.mGoogleMap.setMaxZoomPreference(16.0f);
        this.mLocationRequest = new LocationRequest();
        this.mLocationRequest.setPriority(102);
        this.mGoogleMap.getUiSettings().setMapToolbarEnabled(false);
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(getActivity(), "android.permission.ACCESS_FINE_LOCATION") == 0) {
                this.mFusedLocationClient.requestLocationUpdates(this.mLocationRequest, this.mLocationCallback, Looper.myLooper());
                return;
            } else {
                checkLocationPermission();
                return;
            }
        }
        this.mFusedLocationClient.requestLocationUpdates(this.mLocationRequest, this.mLocationCallback, Looper.myLooper());
    }

    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(getActivity(), "android.permission.ACCESS_FINE_LOCATION") != 0) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), "android.permission.ACCESS_FINE_LOCATION")) {
                new AlertDialog.Builder(getActivity()).setTitle("Location Permission Needed").setMessage("This app needs the Location permission, please accept to use location functionality").setPositiveButton("OK", new DialogInterface.OnClickListener() { // from class: quadrant.mokafat.points.view.single_partner_details.single_partner_tabs.tab2.SingleAllBranchFragment.2
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ActivityCompat.requestPermissions(SingleAllBranchFragment.this.getActivity(), new String[]{"android.permission.ACCESS_FINE_LOCATION"}, 99);
                    }
                }).create().show();
            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{"android.permission.ACCESS_FINE_LOCATION"}, 99);
            }
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 99) {
            if (grantResults.length > 0 && grantResults[0] == 0) {
                if (ContextCompat.checkSelfPermission(getActivity(), "android.permission.ACCESS_FINE_LOCATION") == 0) {
                    this.mFusedLocationClient.requestLocationUpdates(this.mLocationRequest, this.mLocationCallback, Looper.myLooper());
                    return;
                }
                return;
            }
            Toast.makeText(getActivity(), "permission denied", 1).show();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
    }

    @Override // quadrant.mokafat.points.baseClass.BaseView
    public void onAttache() {
    }

    @Override // quadrant.mokafat.points.view.single_partner_details.single_partner_tabs.tab2.VendorBranchesView
    public void showLoading() {
    }

    @Override // quadrant.mokafat.points.view.single_partner_details.single_partner_tabs.tab2.VendorBranchesView
    public void hideLoading() {
    }

    @Override // quadrant.mokafat.points.view.single_partner_details.single_partner_tabs.tab2.VendorBranchesView
    public void showResponse(DataObject dataObject) {
        GlideApp.with(getActivity()).load2("http://199.247.4.89/mokafat/dev/public/uploads/large/" + getActivity().getIntent().getStringExtra("partner_logo")).into(this.imageViewVendorLogo);
        for (int i = 0; i < dataObject.getData().size(); i++) {
            LatLng latLng2 = new LatLng(Double.parseDouble(dataObject.getData().get(i).getAttributes().getLatitude()), Double.parseDouble(dataObject.getData().get(i).getAttributes().getLongitude()));
            MarkerOptions markerOptions2 = new MarkerOptions();
            markerOptions2.position(latLng2);
            markerOptions2.icon(BitmapDescriptorFactory.fromResource(R.drawable.location));
            InfoWindowData windowData = new InfoWindowData();
            windowData.setName(dataObject.getData().get(i).getAttributes().getTitle());
            markerOptions2.title(dataObject.getData().get(i).getAttributes().getAddress());
            markerOptions2.snippet(dataObject.getData().get(i).getAttributes().getPhone() + "\n" + dataObject.getData().get(i).getAttributes().getWorking_hours());
            CustomInfoWindowGoogleMap adapter = new CustomInfoWindowGoogleMap(getActivity(), windowData);
            this.mGoogleMap.setInfoWindowAdapter(adapter);
            this.mGoogleMap.addMarker(markerOptions2);
        }
    }

    @Override // quadrant.mokafat.points.view.single_partner_details.single_partner_tabs.tab2.VendorBranchesView
    public void showMessage(String message) {
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // quadrant.mokafat.points.view.single_partner_details.single_partner_tabs.tab2.VendorBranchesView
    public void showResponseCity(GetBranchesByBranchIdResponse getBranchesByBranchIdResponse) {
        this.listDataHeader = new ArrayList();
        this.listDataChild = new HashMap<>();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < getBranchesByBranchIdResponse.getData().size(); i++) {
            this.listDataHeader.add(getBranchesByBranchIdResponse.getData().get(i).getCity());
            for (int i2 = 0; i2 < getBranchesByBranchIdResponse.getData().get(i).getBranches().size(); i2++) {
                arrayList.add(new BranchesList(getBranchesByBranchIdResponse.getData().get(i).getBranches().get(i2).getAttributes()));
            }
            this.listDataChild.put(this.listDataHeader.get(i), arrayList);
            arrayList = new ArrayList();
        }
        this.listAdapter = new ExpandableBranchesListAdapter(getActivity(), this.listDataHeader, this.listDataChild);
        this.expListView.setAdapter(this.listAdapter);
    }

    @Override // quadrant.mokafat.points.view.single_partner_details.single_partner_tabs.tab2.VendorBranchesView
    public void onComplete() {
    }
}
