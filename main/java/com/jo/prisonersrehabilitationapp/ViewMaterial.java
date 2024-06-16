package com.jo.prisonersrehabilitationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ViewMaterial extends AppCompatActivity {
    DownloadManager manager;

    List<DataAdapter> ListOfdataAdapter;

    RecyclerView recyclerView;

    String HTTP_JSON_URL = "http://"+ServerConnect.serverip +"/Android/prisonersrehabilitation/downloadfiles.php";

    String Image_Name_JSON = "sb_id";
    String Image_URL_JSON = "ser_id";
    String link = "link";
    String Image_Type = "sname";


    String linked;
    JsonArrayRequest RequestOfJSonArray ;

    RequestQueue requestQueue ;

    View view ;

    int RecyclerViewItemPosition ;

    RecyclerView.LayoutManager layoutManagerOfrecyclerView;

    RecyclerView.Adapter recyclerViewadapter;

    ArrayList<String> CID =new ArrayList<>();
    ArrayList<String> REGNO=new ArrayList<>();
    ArrayList<String> CERTIFICATE=new ArrayList<>();



    public static final String SHARED_PREFS1 = "";

    SharedPreferences sharedpreferences1;
    TextView v;
    private int RequestCode = 100;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_material);
        sharedpreferences1 = getSharedPreferences(SHARED_PREFS1, Context.MODE_PRIVATE);

        TextView textView =findViewById(R.id.webside);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define the URL you want to open
                String websiteUrl = linked; // Replace with your website URL

                // Create an Intent with ACTION_VIEW to open the website
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(websiteUrl));

                // Start the activity to open the website
                startActivity(intent);
            }
        });
        // Product_ImageURL = new ArrayList<>();

        ListOfdataAdapter = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview1);

        recyclerView.setHasFixedSize(true);

        layoutManagerOfrecyclerView = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManagerOfrecyclerView);



        JSON_HTTP_CALL();

        // Implementing Click Listener on RecyclerView.
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            GestureDetector gestureDetector = new GestureDetector(ViewMaterial.this, new GestureDetector.SimpleOnGestureListener() {

                @Override public boolean onSingleTapUp(MotionEvent motionEvent) {

                    return true;
                }

            });
            @Override
            public boolean onInterceptTouchEvent(RecyclerView Recyclerview, MotionEvent motionEvent) {

                view = Recyclerview.findChildViewUnder(motionEvent.getX(), motionEvent.getY());

                if(view != null && gestureDetector.onTouchEvent(motionEvent)) {

                    //Getting RecyclerView Clicked Item value.
                    RecyclerViewItemPosition = Recyclerview.getChildAdapterPosition(view);

                   /* manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                    Uri uri = Uri.parse(Url.url+"certificates/"+CERTIFICATE.get(RecyclerViewItemPosition) +".jpg");
                    Log.i("UR:",Url.url+"certificates/"+CERTIFICATE.get(RecyclerViewItemPosition) +".jpg");
                    DownloadManager.Request request = new DownloadManager.Request(uri);
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
                    long reference = manager.enqueue(request);*/

                    downloadPdf(CERTIFICATE.get(RecyclerViewItemPosition));
/*                    Intent ii = new Intent(ApplyTournament.this,ViewApplication.class);
                    //  ii.putExtra("pid",Product_Id.get(RecyclerViewItemPosition));
                    ii.putExtra("clna",College_Name.get(RecyclerViewItemPosition));
                    ii.putExtra("pic",Picture.get(RecyclerViewItemPosition));
                    ii.putExtra("bid",Bus_ID.get(RecyclerViewItemPosition));
                    ii.putExtra("aid",AID.get(RecyclerViewItemPosition));
                    ii.putExtra("stun",Student_Name.get(RecyclerViewItemPosition));
                    ii.putExtra("busd",BusDetails.get(RecyclerViewItemPosition));
                    ii.putExtra("otherd",Otherdetails.get(RecyclerViewItemPosition));
                    ii.putExtra("stud",Stud_Details.get(RecyclerViewItemPosition));

                    startActivity(ii);*/



                    //    alertDialog(ImageTitleNameArrayListForClick.get(RecyclerViewItemPosition),Price.get(RecyclerViewItemPosition),
                    //    Itemid.get(RecyclerViewItemPosition),Phone.get(RecyclerViewItemPosition),FID.get(RecyclerViewItemPosition));
                    //   ImageTitleNameArrayListForClick.add(json.getString(Image_Name_JSON));
                    // Price.add(json.getString(Image_Price));
                    // Itemid.add(json.getString(GID));

                    // Showing RecyclerView Clicked Item value using Toast.
                    // Toast.makeText(UserHome.this, ImageTitleNameArrayListForClick.get(RecyclerViewItemPosition), Toast.LENGTH_LONG).show();
                }

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView Recyclerview, MotionEvent motionEvent) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }


    private void downloadPdf(String fileName) {

        String url = "http://" + ServerConnect.serverip + "/Android/prisonersrehabilitation/uploadedFiles/" + fileName ;
        Log.i("Download", "Attempting to download: " + url);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            requestPermissions(new String[] {android.Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},RequestCode);
        }

        File direct = new File(Environment.getExternalStorageDirectory() + "/Download/AldoFiles");

        if (!direct.exists()) {
            File pdfDirectory = new File("/sdcard/Download/AldoFiles/");
            pdfDirectory.mkdirs();
        }


        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url + ""));
        request.setTitle(fileName);
        // request.setMimeType("application/*");
        request.setMimeType("pdf/*");
        request.allowScanningByMediaScanner();
        request.setAllowedOverMetered(true);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "AldoFiles/" + fileName);
        DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        downloadManager.enqueue(request);

    }


    public void JSON_HTTP_CALL(){

        RequestOfJSonArray = new JsonArrayRequest(HTTP_JSON_URL,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        ParseJSonResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        requestQueue = Volley.newRequestQueue(ViewMaterial.this);

        requestQueue.add(RequestOfJSonArray);
    }

    public void ParseJSonResponse(JSONArray array){

        for(int i = 0; i<array.length(); i++) {

            DataAdapter GetDataAdapter2 = new DataAdapter();

            JSONObject json = null;
            try {

                json = array.getJSONObject(i);

                //GetDataAdapter2.setImageTitle(json.getString(Image_Name_JSON));
                //GetDataAdapter2.setImageType("Download Certificate");
                GetDataAdapter2.setImageType(json.getString(Image_URL_JSON));
                linked=json.getString(link);
                // GetDataAdapter2.setImageSize("Student Name :"+json.getString(Image_Category));
                // GetDataAdapter2.setImagePack(json.getString(Image_Quant));
                // GetDataAdapter2.setImagePrice(json.getString(Image_Price));
                // GetDataAdapter2.setIMGID(json.getString(PID));

                // Adding image title name in array to display on RecyclerView click event.




                CID.add(json.getString(Image_Name_JSON));
                REGNO.add(json.getString(Image_URL_JSON));
                CERTIFICATE.add(json.getString(Image_Type));






                //   GetDataAdapter2.setImageUrl(URL.ur+"products/"+json.getString(Image_URL_JSON)+".jpg");




            } catch (JSONException e) {

                e.printStackTrace();
            }
            ListOfdataAdapter.add(GetDataAdapter2);
        }

        recyclerViewadapter = new RecyclerAllAdapter(ListOfdataAdapter, this);

        recyclerView.setAdapter(recyclerViewadapter);
    }


}
