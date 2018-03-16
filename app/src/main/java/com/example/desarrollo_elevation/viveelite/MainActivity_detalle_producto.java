package com.example.desarrollo_elevation.viveelite;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;

import com.example.desarrollo_elevation.viveelite.Adapter.Model_productos;
import com.example.desarrollo_elevation.viveelite.Adapter.adapter_productos;

import java.util.ArrayList;

public class MainActivity_detalle_producto extends AppCompatActivity {

    private static ArrayList<Model_productos> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_detalle_producto);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Bundle bundle = getIntent().getExtras();

        String titulo = bundle.getString("titulo");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbardetalleproducto);
        toolbar.setTitle(titulo);


        // toolbar.displ.setDisplayHomeAsUpEnabled(true);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.flecha_retorno);


        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        list = new ArrayList<>();
        int numero = bundle.getInt("num");

        if(numero == 1)
        {
            list.add(new Model_productos(1, R.drawable.elite_gold, "ELITE GOLD", "¡Nuevo! Creamos un papel más suave, más resistente y más rendidor."));
            list.add(new Model_productos(1, R.drawable.elite_duo, "ELITE DÚO", "Disponible en paquetes de 4 y 6 rollos."));
            //list.add(new Model_productos(1, R.drawable.elite_big_roll, "BIG ROLL", "Te ofrecemos rollos más rendidores. es el único que cuenta con un práctico empaque individual, protegiendo los rollos de la suciedad, es un producto muy resistente con un suave aroma, la combinación perfecta que se adapta a tu familia y a tu bolsillo."));
            list.add(new Model_productos(1, R.drawable.elite_color, "ELITE COLOR", "Higiénico que evoluciona para traer por primera vez a México una nueva tecnología que encapsula, suaviza y reduce los malos olores del cesto de basura en tu baño, además con su grabado de flores a color le da un toque agradable a la decoración de tu baño. Ideal para el cuidado de tu familia y economía."));
            //list.add(new Model_productos(1, R.drawable.elite_celeste, "ELITE CELESTE", "Si buscas rendimiento, este es el papel higiénico para ti y tu familia. Un higiénico que te brinda resistencia y funcionalidad, además contiene esencias de Aloe Vera. ¡Pruébalo!, comprobarás que gracias a su nuevo grabado con exclusivos círculos de absorción, obtendrás la mejor combinación entre calidad y precio."));
            //list.add(new Model_productos(1, R.drawable.elite_spa, "ELITE SPA", "Este higiénico de edición limitada, fabricado bajo estrictos controles de calidad, además de contar con máxima suavidad y un elegante grabado de flor, tiene un moderno, delicado y fresco aroma a orquídeas que lo distingue. ¡Te encantará!"));
            //list.add(new Model_productos(1, R.drawable.elite_ultra, "ELITE ULTRA", "Suavidad más elegancia, la fórmula perfecta para tu hogar. Este higiénico de alto metraje es fabricado con 100% fibra virgen de bosques renovables, bajo un proceso tecnológico exclusivo que permite ofrecer una extraordinaria blancura y máxima suavidad. su elegante diseño y aroma dan a tu baño un toque agradable y especial."));
            list.add(new Model_productos(1, R.drawable.elite_triplex, "ELITE TRIPLEX", "Cuando piensas en calidad, este higiénico es el ideal. Con tres hojas de suave textura acolchonada, elaboradas con las más puras fibras naturales y extracto de seda, lo hacen ser el único que te garantiza resistencia sin perder su máxima suavidad."));
        }
        else if(numero == 2) {
            list.add(new Model_productos(1, R.drawable.servilletas_elite_gold, "ELITE GOLD", "¡Haz de tus ocasiones ordinarias momentos extraordinarios! Elite te ofrece una extensa línea de servilletas decorativas y de gran suavidad en una práctica presentación. Encuéntralas en colores lisos, divertidos diseños, servilletas de temporada como fiestas patrias y navidad, además de las nuevas servilletas blancas."));
            list.add(new Model_productos(1, R.drawable.servilletas_elite_celeste, "ELITE HOJA SIMPLE", "Servilletas de mesa con textura esponjosita de alto rendimiento, que brindan una excelente absorción y resistencia en toda ocación."));
            list.add(new Model_productos(1, R.drawable.servilletas_elite_ultra, "ELITE ULTRA", "Dale a tu mesa elegancia y distinción, estas servilletas fabricadas con papel tissue celulósico brindan un espectacular nivel de blancura y máxima suavidad, que con su grabado de flores en el borde, hacen especial cada momento en familia."));
        }
        else if(numero == 3) {
            list.add(new Model_productos(1, R.drawable.toalla_elite_gold, "ELITE GOLD JUMBO", "Muy blanca. Nuevo diseño 3D: tecnología que logra un grabado tridimensional y un diseño elegante. Práctico multicore: permite un mayor rendimiento en el uso del producto."));
            list.add(new Model_productos(1, R.drawable.toalla_elite_celeste, "ELITE CELESTE MEGAROLL", "Con una toalla MegaRoll en tu cocina basta para tener practicidad y limpieza al instante. Por su gran cantidad de metros de papel y su innovador multicorte, podrás elegir el tamaño de la hoja a utilizar. Además con su tecnología de gran absorción permite que las hojas encapsulen los líquidos asegurando un desempeño excepcional."));
            //list.add(new Model_productos(1, R.drawable.toalla_elite_ultra, "ELITE ULTRA DISEÑO", "Fabricada con fibras de alta pureza, con un grabado de círculos de absorción, que hacen que sus grandes hojas tengan un mayor desempeño. El alto nivel de blancura que la caracteriza, así como su impresión con elegantes diseños la convierte en una toalla de cocina de máxima calidad."));
        }
        else if(numero == 4) {
            //list.add(new Model_productos(1, R.drawable.panuelos_loonytoons_2, "FACIALES Y PAÑUELOS LOONY TUNES", "“Nuevos Pañuelos y Faciales Elite Looney Tunes” colecciona sus divertidos diseños y descubre sus deliciosos aromas. Disponible en estuche y pañuelos de bolsillo."));
            list.add(new Model_productos(1, R.drawable.panuelos_elite_aromas, "PAÑUELOS ELITE AROMAS", "Nunca puede faltar la ocasión para sentir y disfrutar la suavidad de los pañuelos Elite Aromas, cuya práctica presentación te permite tenerlos siempre contigo. encuéntralos en diferentes y frescos aromas como sandía, manzana, mentol y uva."));
            list.add(new Model_productos(1, R.drawable.panuelo_diseno_elite, "FACIALES Y PAÑUELOS ELITE DISEÑO", "Ten siempre a la mano pañuelos desechables Elite Línea Diseños los cuales con su doble hoja y exclusiva formulación te darán la mejor combinación entre suavidad y resistencia. Busca los diseños exclusivos y haz más divertida tu decoración en el auto, casa u oficina."));
            list.add(new Model_productos(1, R.drawable.panuelos_spa, "FACIALES Y PAÑUELOS ELITE SPA", "Disfruta los aromas relajantes que te ofrecen pañuelos Elite Línea Spa en sus diversas presentaciones, los cuales están fabricados bajo los más altos estándares de calidad, que permiten ofrecer suavidad para cuidar tu piel y la de tu familia. Aromas Coco-Lima, Pepino-Melón, Lavanda y Vainilla."));
            //list.add(new Model_productos(1, R.drawable.panuelo_elite_mentol, "FACIALES Y PAÑUELOS ELITE MENTOL", "Dale a tu nariz un toque de suavidad con el pañuelo mentol aroma refrescante, que gracias a su composición de triple hoja te da un mayor alivio y sensación de frescura al respirar."));
        }

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recicleviwerdetalleproducto);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);
        adapter_productos adapter = new adapter_productos(list, this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}
