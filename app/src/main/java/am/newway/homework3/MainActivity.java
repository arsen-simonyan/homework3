package am.newway.homework3;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

import static am.newway.homework3.TaskType.valueOf;

public class MainActivity extends AppCompatActivity
{
    private final int SECONDACTIVITY = 1;
    private TextView textName;
    private TextView textDescription;
    private ImageView imageView;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        textName = findViewById( R.id.textName );
        textDescription = findViewById( R.id.textDescription );
        imageView = findViewById( R.id.image );

        findViewById( R.id.floatingActionButton ).setOnClickListener( new View.OnClickListener() {
            @Override public void onClick( View v )
            {
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(MainActivity.this, imageView, "aca");
                startActivityForResult( new Intent(MainActivity.this,
                        SecondActivity.class), SECONDACTIVITY, options.toBundle());
            }
        } );
    }

    private void setType(String str )
    {
        ConstraintLayout rl = findViewById(R.id.root);

        switch ( valueOf( str ) ){
            case IMP:
                rl.setBackgroundColor( ContextCompat.getColor( getBaseContext(), R.color.myRed)); break;
            case ORD:
                rl.setBackgroundColor( ContextCompat.getColor( getBaseContext(), R.color.myGreen)); break;
            case UNI:
                rl.setBackgroundColor( ContextCompat.getColor( getBaseContext(), R.color.myBlue)); break;
        }
    }

    @Override protected void onActivityResult( int requestCode , int resultCode , @Nullable Intent data )
    {

        if(requestCode == SECONDACTIVITY && resultCode == RESULT_OK && data != null)
        {
            Bundle bnd = data.getExtras(  );
            if (bnd != null)
            {
                textName.setText( Objects.requireNonNull( bnd.get( "name" ) ).toString() );
                textDescription.setText( Objects.requireNonNull( bnd.get( "desc" ) ).toString() );
                Uri myUri = Uri.parse(bnd.getString( "uri" ));
                imageView.setImageURI( myUri );
                setType(bnd.getString( "type" ));
            }
        }
        super.onActivityResult( requestCode , resultCode , data );
    }
}
