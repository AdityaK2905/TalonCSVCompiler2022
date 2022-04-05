package com.example.filecompiler;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file_list);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        String path = getIntent().getStringExtra("path");
        File root = new File(path);
        File[] filesAndFolders = root.listFiles();


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(),filesAndFolders));


        ArrayList<File> filesToCompile;

    }


    public void addFilesFunction(View v){
        EditText fileName =(EditText) findViewById(R.id.editFileName);

        File file= new File(Constants.SCOUTING_DIR);
        File[] list = file.listFiles();

        String fileNameText = fileName.getText().toString();
        String FILENAME =(""+fileNameText+".csv");

        File currentFile = new File(Constants.SCOUTING_DIR, FILENAME);
    try {
        FileWriter writer = new FileWriter(currentFile);

        String entry = "";
        /*

        ArrayList<File> filesToCompile = new ArrayList<>();
        for (File f : list ){
            filesToCompile.add(f);
        }
        */
        for (File f : list) {
            entry = "";
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()){
                entry = entry + sc.nextLine();
            }
            entry += "\n";
            writer.append(entry);

        }
        writer.flush();
        writer.close();
        Toast toast = Toast.makeText(getApplicationContext(), "File Downloaded! Check Directory.", Toast.LENGTH_LONG);
        toast.show();
    } catch (Exception e){

    }

    }
}
