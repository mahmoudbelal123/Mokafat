package quadrant.mokafat.points.view.get_location_map.near_by_tabs.near_by_tabs_adapter.tab1;

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
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
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
import java.util.List;
import java.util.Locale;
import javax.inject.Inject;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.dagger.DaggerApplication;
import quadrant.mokafat.points.models.objects.get_branches.GetBranchesResponse;
import quadrant.mokafat.points.models.objects.get_items.GetItemsResponse;
import quadrant.mokafat.points.view.get_location_map.CustomInfoWindowGoogleMap;
import quadrant.mokafat.points.view.get_location_map.InfoWindowData;

/* JADX INFO: loaded from: classes.dex */
public class NearByDealsFragment extends Fragment implements OnMapReadyCallback, View.OnClickListener, GetNearbyDealsView, GetItemsView {
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    String address;
    List<Address> addresses;
    Geocoder geocoder;

    @Inject
    GetItemsPresenter getItemsPresenter;
    private FrameLayout imageGetMyLocation;
    String lat;
    String lon;
    Marker mCurrLocationMarker;
    FusedLocationProviderClient mFusedLocationClient;
    GoogleMap mGoogleMap;
    Location mLastLocation;
    LocationRequest mLocationRequest;
    SupportMapFragment mapFrag;
    Marker marker;

    @Inject
    GetNearbyDealsPresenter nearbyDealsPresenter;
    private FrameLayout progressGetBranches;
    RecyclerView recyclerViewItems;
    private View view;
    List<Integer> vendorsIds = new ArrayList();
    LocationCallback mLocationCallback = new LocationCallback() { // from class: quadrant.mokafat.points.view.get_location_map.near_by_tabs.near_by_tabs_adapter.tab1.NearByDealsFragment.2
        @Override // com.google.android.gms.location.LocationCallback
        public void onLocationResult(LocationResult locationResult) {
            try {
                List<Location> locationList = locationResult.getLocations();
                Location location = locationList.get(locationList.size() - 1);
                NearByDealsFragment.this.geocoder = new Geocoder(NearByDealsFragment.this.getActivity(), Locale.getDefault());
                double lat = location.getLatitude();
                double lon = location.getLongitude();
                NearByDealsFragment.this.addresses = NearByDealsFragment.this.geocoder.getFromLocation(lat, lon, 1);
                NearByDealsFragment.this.address = NearByDealsFragment.this.addresses.get(0).getAddressLine(0);
                NearByDealsFragment.this.addresses.get(0).getLocality();
                NearByDealsFragment.this.addresses.get(0).getAdminArea();
                NearByDealsFragment.this.addresses.get(0).getCountryName();
                NearByDealsFragment.this.addresses.get(0).getPostalCode();
                NearByDealsFragment.this.addresses.get(0).getFeatureName();
                if (locationList.size() > 0) {
                    Log.i("MapsActivity", "Location: " + location.getLatitude() + " " + location.getLongitude());
                    NearByDealsFragment.this.mLastLocation = location;
                    location.getLatitude();
                    location.getLongitude();
                    LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(latLng);
                    markerOptions.title(NearByDealsFragment.this.address);
                    markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.my_location_icon));
                    InfoWindowData info = new InfoWindowData();
                    info.setName("Name");
                    info.setAddress("Address");
                    info.setPhone("Phone");
                    info.setWorkingHours("10: am");
                    CustomInfoWindowGoogleMap customInfoWindow = new CustomInfoWindowGoogleMap(NearByDealsFragment.this.getActivity(), info);
                    NearByDealsFragment.this.mGoogleMap.setInfoWindowAdapter(customInfoWindow);
                    NearByDealsFragment.this.mCurrLocationMarker = NearByDealsFragment.this.mGoogleMap.addMarker(markerOptions);
                    NearByDealsFragment.this.mCurrLocationMarker.setTag(info);
                    NearByDealsFragment.this.nearbyDealsPresenter.getLocation(location);
                    NearByDealsFragment.this.mGoogleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() { // from class: quadrant.mokafat.points.view.get_location_map.near_by_tabs.near_by_tabs_adapter.tab1.NearByDealsFragment.2.1
                        @Override // com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
                        public boolean onMarkerClick(Marker marker) {
                            NearByDealsFragment.this.mCurrLocationMarker.showInfoWindow();
                            return false;
                        }
                    });
                    double lat2 = location.getLatitude();
                    CameraUpdateFactory.newLatLng(new LatLng(lat2, location.getLongitude()));
                    CameraUpdate zoom = CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 13.0f);
                    NearByDealsFragment.this.mGoogleMap.animateCamera(zoom);
                    NearByDealsFragment.this.nearbyDealsPresenter.getNearbyDealsBranches();
                }
            } catch (Exception e) {
            }
        }
    };
    List<GetBranchesResponse> getItemsResponse = new ArrayList();
    InfoWindowData info = new InfoWindowData();

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_near_by_deals, container, false);
        this.imageGetMyLocation = (FrameLayout) this.view.findViewById(R.id.imageView18_get_my_location);
        this.recyclerViewItems = (RecyclerView) this.view.findViewById(R.id.recycler_view_items_vendors);
        this.progressGetBranches = (FrameLayout) this.view.findViewById(R.id.frame_layout_progress);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), 1, false);
        this.recyclerViewItems.setLayoutManager(mLayoutManager);
        this.recyclerViewItems.setItemAnimator(new DefaultItemAnimator());
        this.recyclerViewItems.setLayoutManager(mLayoutManager);
        ((DaggerApplication) getActivity().getApplication()).getAppComponent().inject(this);
        this.nearbyDealsPresenter.onAttach((GetNearbyDealsView) this);
        this.getItemsPresenter.onAttach((GetItemsView) this);
        this.mFusedLocationClient = LocationServices.getFusedLocationProviderClient((Activity) getActivity());
        this.mapFrag = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        this.mapFrag.getMapAsync(this);
        this.imageGetMyLocation.setOnClickListener(new View.OnClickListener() { // from class: quadrant.mokafat.points.view.get_location_map.near_by_tabs.near_by_tabs_adapter.tab1.NearByDealsFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (NearByDealsFragment.this.mGoogleMap != null) {
                    if ((ActivityCompat.checkSelfPermission(NearByDealsFragment.this.getActivity(), "android.permission.ACCESS_FINE_LOCATION") == 0 || ActivityCompat.checkSelfPermission(NearByDealsFragment.this.getActivity(), "android.permission.ACCESS_COARSE_LOCATION") == 0) && NearByDealsFragment.this.mGoogleMap != null && NearByDealsFragment.this.mLocationCallback != null && NearByDealsFragment.this.mLocationRequest != null) {
                        NearByDealsFragment.this.mGoogleMap.clear();
                        NearByDealsFragment.this.mFusedLocationClient.requestLocationUpdates(NearByDealsFragment.this.mLocationRequest, NearByDealsFragment.this.mLocationCallback, Looper.myLooper());
                    }
                }
            }
        });
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
                new AlertDialog.Builder(getActivity()).setTitle("Location Permission Needed").setMessage("This app needs the Location permission, please accept to use location functionality").setPositiveButton("OK", new DialogInterface.OnClickListener() { // from class: quadrant.mokafat.points.view.get_location_map.near_by_tabs.near_by_tabs_adapter.tab1.NearByDealsFragment.3
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ActivityCompat.requestPermissions(NearByDealsFragment.this.getActivity(), new String[]{"android.permission.ACCESS_FINE_LOCATION"}, 99);
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
        if (v.getId() == R.id.toolbar_back_near_by) {
            getActivity().finish();
        }
    }

    @Override // quadrant.mokafat.points.baseClass.BaseView
    public void onAttache() {
    }

    @Override // quadrant.mokafat.points.view.get_location_map.near_by_tabs.near_by_tabs_adapter.tab1.GetNearbyDealsView, quadrant.mokafat.points.view.get_location_map.near_by_tabs.near_by_tabs_adapter.tab1.GetItemsView
    public void showLoading() {
        this.progressGetBranches.setVisibility(0);
    }

    @Override // quadrant.mokafat.points.view.get_location_map.near_by_tabs.near_by_tabs_adapter.tab1.GetNearbyDealsView, quadrant.mokafat.points.view.get_location_map.near_by_tabs.near_by_tabs_adapter.tab1.GetItemsView
    public void hideLoading() {
        this.progressGetBranches.setVisibility(4);
    }

    @Override // quadrant.mokafat.points.view.get_location_map.near_by_tabs.near_by_tabs_adapter.tab1.GetItemsView
    public void showResponse(GetItemsResponse getItemsResponse) {
        ItemsAdapter adapter = new ItemsAdapter(getItemsResponse.getData(), getActivity());
        this.recyclerViewItems.setAdapter(adapter);
    }

    @Override // quadrant.mokafat.points.view.get_location_map.near_by_tabs.near_by_tabs_adapter.tab1.GetNearbyDealsView
    public void showResponse(quadrant.mokafat.points.models.objects.get_branches.test_get_branches.GetBranchesResponse getItemsResponse) {
        for (int i = 0; i < getItemsResponse.getData().size(); i++) {
            this.vendorsIds.add(Integer.valueOf(getItemsResponse.getData().get(i).getAttributes().getVendor_id()));
            LatLng latLng2 = new LatLng(Double.parseDouble(getItemsResponse.getData().get(i).getAttributes().getLatitude()), Double.parseDouble(getItemsResponse.getData().get(i).getAttributes().getLongitude()));
            MarkerOptions markerOptions2 = new MarkerOptions();
            markerOptions2.position(latLng2);
            markerOptions2.icon(BitmapDescriptorFactory.fromResource(R.drawable.location));
            InfoWindowData windowData = new InfoWindowData();
            windowData.setName(getItemsResponse.getData().get(i).getAttributes().getTitle());
            markerOptions2.title(getItemsResponse.getData().get(i).getAttributes().getAddress());
            markerOptions2.snippet(getItemsResponse.getData().get(i).getAttributes().getPhone() + "\n" + getItemsResponse.getData().get(i).getAttributes().getWorking_hours());
            CustomInfoWindowGoogleMap adapter = new CustomInfoWindowGoogleMap(getActivity(), windowData);
            this.mGoogleMap.setInfoWindowAdapter(adapter);
            this.mGoogleMap.addMarker(markerOptions2);
        }
    }

    @Override // quadrant.mokafat.points.view.get_location_map.near_by_tabs.near_by_tabs_adapter.tab1.GetNearbyDealsView, quadrant.mokafat.points.view.get_location_map.near_by_tabs.near_by_tabs_adapter.tab1.GetItemsView
    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, 1).show();
    }

    @Override // quadrant.mokafat.points.view.get_location_map.near_by_tabs.near_by_tabs_adapter.tab1.GetNearbyDealsView
    public void onComplete() {
        this.getItemsPresenter.getItems();
    }

    @Override // quadrant.mokafat.points.view.get_location_map.near_by_tabs.near_by_tabs_adapter.tab1.GetItemsView
    public List<Integer> getVendorIds() {
        return this.vendorsIds;
    }

    @Override // quadrant.mokafat.points.view.get_location_map.near_by_tabs.near_by_tabs_adapter.tab1.GetNearbyDealsView
    public String getLatitude() {
        return this.lat;
    }

    @Override // quadrant.mokafat.points.view.get_location_map.near_by_tabs.near_by_tabs_adapter.tab1.GetNearbyDealsView
    public String getLongitute() {
        return this.lon;
    }
}
