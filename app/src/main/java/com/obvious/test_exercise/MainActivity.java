package com.obvious.test_exercise;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.obvious.test_exercise.adapters.ImagesAdapter;
import com.obvious.test_exercise.model.DataModel;
import com.obvious.test_exercise.receiver.ConnectivityReceiver;
import com.obvious.test_exercise.repository.ImagesRespository;
import com.obvious.test_exercise.utils.Util;
import com.obvious.test_exercise.viewModel.ImageViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ImageViewModel, ConnectivityReceiver.ConnectivityReceiverListener {
    private RecyclerView recyclerView;
    private Context context;
    private ArrayList<DataModel> imagesArrayList;
    private AlertDialog alertDialog;
    private Boolean isShown = false;
    private ConnectivityReceiver mConnectivityReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignIds();

    }

    private void assignIds() {
        context = this;
        // Register Connection reciever
        mConnectivityReceiver = new ConnectivityReceiver(this);
        recyclerView = findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);
        // setting layout manager with 3 cells in a row on recyclerView
        recyclerView.setLayoutManager(gridLayoutManager);
        // init Images Repo
        ImagesRespository imagesRespository = new ImagesRespository();
        imagesRespository.getImagesArrayList(context);

    }

    // calling details activity here from #ImagesAdapter class
    public void startNewActivity(int adapterPosition, View imageView) {
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra("imageList", imagesArrayList);
        intent.putExtra("position", adapterPosition);
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this, (View) imageView, "animation");
        startActivity(intent, options.toBundle());
    }

    @Override
    public void onBackPressed() {
        exitFromapp();
    }

    // App exit confirmation dialog
    private void exitFromapp() {
        Util.showAlert(context, context.getResources().getString(R.string.exit_confirmation), context.getResources().getString(R.string.yes), context.getResources().getString(R.string.no), true);

    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        if (!isConnected)
            showOrDismissNoInternetDialog(this, true);
        else
            showOrDismissNoInternetDialog(this, false);

    }

    public void showOrDismissNoInternetDialog(Context context, Boolean show) {
        if (show) {
            alertDialog = Util.showAlert(context, context.getResources().getString(R.string.no_internet), context.getResources().getString(R.string.retry), null, false);
            isShown = true;
        } else {
            if (isShown) {
                alertDialog.dismiss();
                isShown = false;
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(mConnectivityReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mConnectivityReceiver);
    }

    @Override
    public void getImagesArrayList(ArrayList<DataModel> imagesArrayList) {
        if (imagesArrayList.size() > 0) {
            this.imagesArrayList = imagesArrayList;
            // initalising adapter class
            ImagesAdapter imagesAdapter = new ImagesAdapter(context, imagesArrayList);
            //setting adapter on recycleview
            recyclerView.setAdapter(imagesAdapter);
        } else {
            Toast.makeText(context, "No Images Available", Toast.LENGTH_SHORT).show();
        }

    }
}