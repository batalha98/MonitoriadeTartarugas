package com.example.monitoriadetartarugas;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.monitoriadetartarugas.addData.AddActivities;
import com.example.monitoriadetartarugas.addData.AddBeach;
import com.example.monitoriadetartarugas.addData.AddHabitat;
import com.example.monitoriadetartarugas.addData.AddIsland;
import com.example.monitoriadetartarugas.addData.AddSpecie;
import com.example.monitoriadetartarugas.addData.AddUser;
import com.example.monitoriadetartarugas.addData.AddWC;
import com.example.monitoriadetartarugas.addData.AddWD;
import com.example.monitoriadetartarugas.database.DataOpenHelper;
import com.example.monitoriadetartarugas.domain.controller.ActivitiesController;
import com.example.monitoriadetartarugas.domain.controller.BeachController;
import com.example.monitoriadetartarugas.domain.controller.HabitatController;
import com.example.monitoriadetartarugas.domain.controller.IslandController;
import com.example.monitoriadetartarugas.domain.controller.SpecieController;
import com.example.monitoriadetartarugas.domain.controller.UsersController;
import com.example.monitoriadetartarugas.domain.controller.WindCategoryController;
import com.example.monitoriadetartarugas.domain.controller.WindDirectionController;
import com.example.monitoriadetartarugas.domain.entitys.Habitat;
import com.example.monitoriadetartarugas.domain.entitys.Specie;
import com.example.monitoriadetartarugas.domain.entitys.WindCategory;
import com.example.monitoriadetartarugas.domain.entitys.WindDirection;
import com.example.monitoriadetartarugas.listViewAdapter.ActivitiesAdapter;
import com.example.monitoriadetartarugas.listViewAdapter.BeachAdapter;
import com.example.monitoriadetartarugas.listViewAdapter.HabitatAdapter;
import com.example.monitoriadetartarugas.listViewAdapter.IslandAdapter;
import com.example.monitoriadetartarugas.listViewAdapter.SpeciesAdapter;
import com.example.monitoriadetartarugas.listViewAdapter.UsersAdapter;
import com.example.monitoriadetartarugas.listViewAdapter.WcAdapter;
import com.example.monitoriadetartarugas.listViewAdapter.WdAdapter;

public class ModalBottomSheet extends BottomSheetDialogFragment {
    private DataOpenHelper dataOpenHelper;
    private SQLiteDatabase connection;
    private HabitatController habitatController;
    private UsersController usersController;
    private ActivitiesController activitiesController;
    private BeachController beachController;
    private IslandController islandController;
    private WindCategoryController wcController;
    private WindDirectionController wdController;
    private SpecieController specieController;

    private HabitatAdapter habitatAdapter;
    private SpeciesAdapter speciesAdapter;
    private ActivitiesAdapter activitiesAdapter;
    private UsersAdapter usersAdapter;
    private BeachAdapter beachAdapter;
    private IslandAdapter islandAdapter;
    private WcAdapter wcAdapter;
    private WdAdapter wdAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.modal_content, container, false);
        Button btn_remove = view.findViewById(R.id.btn_remove);
        Button btn_edit = view.findViewById(R.id.btn_edit);
        final Bundle bundle = getArguments();
        String str;
        final String[] strings;

        str = bundle.getString("ToModify");
        strings = str.split("!");

        dataOpenHelper = new DataOpenHelper(getContext());

        btn_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(strings[0].equals("users")){
                    connection = dataOpenHelper.getWritableDatabase();
                    usersController = new UsersController(connection);
                    usersAdapter = new UsersAdapter(usersController.fetchAll(),getContext());

                    usersAdapter.remove(usersController.fetchOne(strings[1]));
                    usersController.remove(strings[1]);

                    Bundle bundle = new Bundle();
                    bundle.putString("idBtn","users");

                    Intent intent = new Intent(getContext(), ListingData.class);
                    intent.putExtras(bundle);
                    startActivityForResult(intent,0);
                }else if(strings[0].equals("activities")){
                    connection = dataOpenHelper.getWritableDatabase();
                    activitiesController = new ActivitiesController(connection);
                    activitiesAdapter = new ActivitiesAdapter(activitiesController.fetchAll(),getContext());

                    activitiesAdapter.remove(activitiesController.fetchOne(strings[1]));
                    activitiesController.remove(strings[1]);

                    Bundle bundle = new Bundle();
                    bundle.putString("idBtn","activities");

                    Intent intent = new Intent(getContext(), ListingData.class);
                    intent.putExtras(bundle);
                    startActivityForResult(intent,0);
                }else if(strings[0].equals("beach")){
                    connection = dataOpenHelper.getWritableDatabase();
                    beachController = new BeachController(connection);
                    beachAdapter = new BeachAdapter(beachController.fetchAll(),getContext());

                    beachAdapter.remove(beachController.fetchOne(strings[1]));
                    beachController.remove(strings[1]);

                    Bundle bundle = new Bundle();
                    bundle.putString("idBtn","beach");

                    Intent intent = new Intent(getContext(), ListingData.class);
                    intent.putExtras(bundle);
                    startActivityForResult(intent,0);
                }else if(strings[0].equals("island")){
                    connection = dataOpenHelper.getWritableDatabase();
                    islandController = new IslandController(connection);
                    islandAdapter = new IslandAdapter(islandController.fetchAll(),getContext());

                    islandAdapter.remove(islandController.fetchOne(strings[1]));
                    islandController.remove(strings[1]);

                    Bundle bundle = new Bundle();
                    bundle.putString("idBtn","island");

                    Intent intent = new Intent(getContext(), ListingData.class);
                    intent.putExtras(bundle);
                    startActivityForResult(intent,0);
                }else if(strings[0].equals("wc")){
                    connection = dataOpenHelper.getWritableDatabase();
                    wcController = new WindCategoryController(connection);
                    wcAdapter = new WcAdapter(wcController.fetchAll(),getContext());

                    WindCategory windCategory = wcController.fetchOne(strings[1]);

                    wcAdapter.remove(windCategory);
                    wcController.remove(windCategory.getIdwc());

                    Bundle bundle = new Bundle();
                    bundle.putString("idBtn","wc");

                    Intent intent = new Intent(getContext(), ListingData.class);
                    intent.putExtras(bundle);
                    startActivityForResult(intent,0);
                }else if(strings[0].equals("wd")){
                    connection = dataOpenHelper.getWritableDatabase();
                    wdController = new WindDirectionController(connection);
                    wdAdapter = new WdAdapter(wdController.fetchAll(),getContext());

                    WindDirection windDirection = wdController.fetchOne(strings[1]);

                    wdAdapter.remove(windDirection);
                    wdController.remove(windDirection.getIdwd());

                    Bundle bundle = new Bundle();
                    bundle.putString("idBtn","wd");

                    Intent intent = new Intent(getContext(), ListingData.class);
                    intent.putExtras(bundle);
                    startActivityForResult(intent,0);
                }else if(strings[0].equals("specie")){
                    connection = dataOpenHelper.getWritableDatabase();
                    specieController = new SpecieController(connection);
                    speciesAdapter = new SpeciesAdapter(specieController.fetchAll(),getContext());

                    Specie specie = specieController.fetchOne(strings[1]);

                    speciesAdapter.remove(specie);
                    specieController.remove(specie.getIdspecie());

                    Bundle bundle = new Bundle();
                    bundle.putString("idBtn","specie");

                    Intent intent = new Intent(getContext(), ListingData.class);
                    intent.putExtras(bundle);
                    startActivityForResult(intent,0);
                }else if(strings[0].equals("habitat")){
                    connection = dataOpenHelper.getWritableDatabase();
                    habitatController = new HabitatController(connection);
                    habitatAdapter = new HabitatAdapter(habitatController.fetchAll(),getContext());

                    Habitat habitat = habitatController.fetchOne(strings[1]);

                    habitatAdapter.remove(habitat);
                    habitatController.remove(habitat.getIdhabitat());

                    Bundle bundle = new Bundle();
                    bundle.putString("idBtn","habitat");

                    Intent intent = new Intent(getContext(), ListingData.class);
                    intent.putExtras(bundle);
                    startActivityForResult(intent,0);
                }
            }
        });

        btn_edit.setOnClickListener(new View.OnClickListener() {
            Bundle bundle;

            @Override
            public void onClick(View v) {
                if(strings[0].equals("users")) {
                    bundle = new Bundle();
                    bundle.putString("user", strings[1]);

                    Intent intent = new Intent(getContext(), AddUser.class);
                    intent.putExtras(bundle);
                    startActivityForResult(intent, 0);
                }else if(strings[0].equals("activities")){
                    bundle = new Bundle();
                    bundle.putString("activities", strings[1]);

                    Intent intent = new Intent(getContext(), AddActivities.class);
                    intent.putExtras(bundle);
                    startActivityForResult(intent, 0);
                }else if(strings[0].equals("beach")){
                    bundle = new Bundle();
                    bundle.putString("beach", strings[1]);

                    Intent intent = new Intent(getContext(), AddBeach.class);
                    intent.putExtras(bundle);
                    startActivityForResult(intent, 0);
                }else if(strings[0].equals("island")){
                    bundle = new Bundle();
                    bundle.putString("island", strings[1]);

                    Intent intent = new Intent(getContext(), AddIsland.class);
                    intent.putExtras(bundle);
                    startActivityForResult(intent, 0);
                }else if(strings[0].equals("wc")){
                    bundle = new Bundle();
                    bundle.putString("wc", strings[1]);

                    Intent intent = new Intent(getContext(), AddWC.class);
                    intent.putExtras(bundle);
                    startActivityForResult(intent, 0);
                }else if(strings[0].equals("wd")){
                    bundle = new Bundle();
                    bundle.putString("wd", strings[1]);

                    Intent intent = new Intent(getContext(), AddWD.class);
                    intent.putExtras(bundle);
                    startActivityForResult(intent, 0);
                }else if(strings[0].equals("specie")){
                    bundle = new Bundle();
                    bundle.putString("specie", strings[1]);

                    Intent intent = new Intent(getContext(), AddSpecie.class);
                    intent.putExtras(bundle);
                    startActivityForResult(intent, 0);
                }else if(strings[0].equals("habitat")){
                    bundle = new Bundle();
                    bundle.putString("habitat", strings[1]);

                    Intent intent = new Intent(getContext(), AddHabitat.class);
                    intent.putExtras(bundle);
                    startActivityForResult(intent, 0);
                }
            }
        });

        return view;
    }
}
