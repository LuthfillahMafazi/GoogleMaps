package co.luthfillahmafazi.googlemaps;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

public class PlacePickerActivity extends AppCompatActivity {

    TextView txtInfoPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_picker);
        txtInfoPlace = findViewById(R.id.txt_info_place);
    }

    public void pickPlace(View view) {
        // Membuat object Place Picker
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

        try {
            startActivityForResult(builder.build(PlacePickerActivity.this),1);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode != 0) {
            // Menampung data dari place picker
            Place place = PlacePicker.getPlace(PlacePickerActivity.this, data);
            // Menampilkan info ke dalam String
            String informasi = String.format("Place : %s \n alamat : %s \n latlong : %s",
                    place.getName(), place.getAddress(), place.getLatLng());
            // tampilkan ke textView
            txtInfoPlace.setText(informasi);
        }
    }
}
