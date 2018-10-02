package com.example.emmchierchie.gethubapp.View;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.emmchierchie.gethubapp.Model.POJO.Repository;
import com.example.emmchierchie.gethubapp.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityRepoDetail extends AppCompatActivity {

    public static final String NAME = "NAME";
    public static final String DESCRIPTION = "DESCRIPTION";
    public static final String STARGAZERS_COUNT = "STARGAZERS_COUNT";
    public static final String FORKS_COUNT = "FORKS_COUNT";

    private String name;
    private String description;
    private Integer stargazers_count;
    private Integer forks_count;

    @BindView(R.id.textViewName)
    TextView textViewName;

    @BindView(R.id.textViewDescription)
    TextView textViewDescription;

    @BindView(R.id.textViewStarsCount)
    TextView textViewStarsCount;

    @BindView(R.id.textViewForks)
    TextView textViewForks;

    @BindView(R.id.toolbar)
    android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_repo_detail );
        ButterKnife.bind( this );

        // toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // back button
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Bundle bundle = getIntent().getExtras();

        // guardo en variables lo que recibo por bundle
        if (bundle != null) {
            name = bundle.getString( NAME );
            description = bundle.getString( DESCRIPTION );
            stargazers_count = bundle.getInt( STARGAZERS_COUNT );
            forks_count = bundle.getInt( FORKS_COUNT );
        }
        UpdateResults();
    }

    // seteo la info de variables a cada vista
    private void UpdateResults() {
        textViewName.setText(name);
        textViewDescription.setText(description);
        textViewStarsCount.setText(stargazers_count.toString());
        textViewForks.setText(forks_count.toString());
    }
}
