package com.uta.utacarrental.view.search_for_car;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.uta.utacarrental.R;
import com.uta.utacarrental.model.Car;

public class SearchForCarFragment extends Fragment {

    private SearchForCarViewModel searchForCarViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        searchForCarViewModel =
                ViewModelProviders.of(this).get(SearchForCarViewModel.class);
        View root = inflater.inflate(R.layout.fragment_search_car, container, false);

      master
        //generateCar();
        return root;
    }
    public void generateCar(){
        Car car1 = new Car();
        car1.setCarName("smart");
        car1.setCarNumber(1);
        car1.setCapacity(1);
        car1.setWeekdayRate(32.99);
        car1.setWeekendRate(37.99);
        car1.setWeekRate(230.93);
        car1.setGPSRate(3.0);
        car1.setOnStartRate(5.0);
        car1.setSiriusXMRate(7.0);
        car1.save();

        Car car2 = new Car();
        car2.setCarName("economy");
        car2.setCarNumber(2);
        car2.setCapacity(3);
        car2.setWeekdayRate(39.99);
        car2.setWeekendRate(44.99);
        car2.setWeekRate(279.93);
        car2.setGPSRate(3.0);
        car2.setOnStartRate(5.0);
        car2.setSiriusXMRate(7.0);
        car2.save();

        Car car3 = new Car();
        car3.setCarName("compact");
        car3.setCarNumber(3);
        car3.setCapacity(4);
        car3.setWeekdayRate(44.99);
        car3.setWeekendRate(49.99);
        car3.setWeekRate(314.93);
        car3.setGPSRate(3.0);
        car3.setOnStartRate(5.0);
        car3.setSiriusXMRate(7.0);
        car3.save();

        Car car4 = new Car();
        car4.setCarName("intermediate");
        car4.setCarNumber(4);
        car4.setCapacity(4);
        car4.setWeekdayRate(45.99);
        car4.setWeekendRate(50.99);
        car4.setWeekRate(321.93);
        car4.setGPSRate(3.0);
        car4.setOnStartRate(5.0);
        car4.setSiriusXMRate(7.0);
        car4.save();

        Car car5 = new Car();
        car5.setCarName("standard");
        car5.setCarNumber(5);
        car5.setCapacity(5);
        car5.setWeekdayRate(48.99);
        car5.setWeekendRate(53.99);
        car5.setWeekRate(342.93);
        car5.setGPSRate(3.0);
        car5.setOnStartRate(5.0);
        car5.setSiriusXMRate(7.0);
        car5.save();

        Car car6 = new Car();
        car6.setCarName("full Size");
        car6.setCarNumber(6);
        car6.setCapacity(6);
        car6.setWeekdayRate(52.99);
        car6.setWeekendRate(57.99);
        car6.setWeekRate(370.93);
        car6.setGPSRate(3.0);
        car6.setOnStartRate(5.0);
        car6.setSiriusXMRate(7.0);
        car6.save();

        Car car7 = new Car();
        car7.setCarName("suv");
        car7.setCarNumber(7);
        car7.setCapacity(8);
        car7.setWeekdayRate(59.99);
        car7.setWeekendRate(64.99);
        car7.setWeekRate(419.93);
        car7.setGPSRate(3.0);
        car7.setOnStartRate(5.0);
        car7.setSiriusXMRate(7.0);
        car7.save();

        Car car8 = new Car();
        car8.setCarName("minivan");
        car8.setCarNumber(8);
        car8.setCapacity(9);
        car8.setWeekdayRate(59.99);
        car8.setWeekendRate(64.99);
        car8.setWeekRate(419.93);
        car8.setGPSRate(3.0);
        car8.setOnStartRate(5.0);
        car8.setSiriusXMRate(7.0);
        car8.save();

        Car car9 = new Car();
        car9.setCarName("ultra sports");
        car9.setCarNumber(9);
        car9.setCapacity(2);
        car9.setWeekdayRate(199.99);
        car9.setWeekendRate(204.99);
        car9.setWeekRate(1399.93);
        car9.setGPSRate(5.0);
        car9.setOnStartRate(7.0);
        car9.setSiriusXMRate(9.0);
        car9.save();
    }
}