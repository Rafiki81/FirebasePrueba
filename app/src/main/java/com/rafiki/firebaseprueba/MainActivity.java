package com.rafiki.firebaseprueba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        //Crea un registro dentro de la coleccion message
       // FirebaseDatabase database = FirebaseDatabase.getInstance();
       // DatabaseReference myRef = database.getReference("message");
       // myRef.setValue("Hello, World!");

    }

    public void createPelicula(View view){
        //Creamos un nuevo nodo pelicula
        DatabaseReference peliculasRef = myRef.child("Peliculas");
        Pelicula pelicula = crearPelicula();

        HashMap<String,Pelicula> peliculas = new HashMap<>(); //Objeto clave valor, clave el id, valor el objeto, en este caso la pelicula
        peliculas.put(String.valueOf(pelicula.getId()),pelicula);
        //añadimos la pelicula con su id como nodo.
        peliculasRef.setValue(peliculas);

    }

    public void updatePelicula(View view){
        DatabaseReference peliculasRef = myRef.child("Peliculas");
        Pelicula pelicula = crearPelicula();
        pelicula.setOriginalTitle("La nueva Pelicula");
        HashMap<String,Object> peliculas = new HashMap<>(); //Objeto clave valor, clave el id, valor el objeto, en este caso la pelicula
        peliculas.put(String.valueOf(pelicula.getId()),pelicula);
        //añadimos la pelicula con su id como nodo.
        peliculasRef.updateChildren(peliculas);

    }

    public void getPelicula(View view) {
        DatabaseReference peliculasRef = myRef.child("Peliculas").child("419704");
        peliculasRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Pelicula pelicula = dataSnapshot.getValue(Pelicula.class);
                System.out.println(pelicula.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("Error al recuperar los datos");
            }
        });

    }

    private Pelicula crearPelicula(){

        Pelicula pelicula = new Pelicula();

        pelicula.setId(419704);
        pelicula.setPopularity(53899);
        pelicula.setVoteCount(3199);
        pelicula.setVideo(true);
        pelicula.setPosterPath("/gkveoprgjeriopjerwpforfdklewop`.jpg");
        pelicula.setAdult(true);
        pelicula.setBackdropPath("/gjreiojeiorfjfewiojgoreithoierw.jpg");
        pelicula.setOriginalLanguage("en");
        pelicula.setOriginalTitle("Ad Astra");
        pelicula.setTitle("Ad Astra");
        pelicula.setVoteAverage(6);
        pelicula.setOverView("Roy McBride es un ingeniero que perdió a su padre en una misión sin retorno a Neptuno para encontrar signos de inteligencia extraterrestre. 20 años después, emprenderá su propio viaje a través del sistema solar para tratar de encontrarlo de nuevo y resolver los misterios del porqué esa primera misión fracasó.");
        pelicula.setReleaseDate("2019-09-17");

        pelicula.setGenreIds(new ArrayList<Integer>(){{
            add(18);
            add(878);
        }});
        return pelicula;
    }
}