package nl.dut.ide.software.yvontelefoon1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToCreateAccount3Student(View view) {
        Intent intent = new Intent (MainActivity.this, CreateAccount3Student.class);
        startActivity(intent);
        return;
    }
}
