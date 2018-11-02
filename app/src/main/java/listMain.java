import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.iparty.R;
import com.iparty.model.Guests;

import java.util.ArrayList;

public class listMain extends AppCompatActivity {

    private ListView listaContatos;
    private ListaContatoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convidados);

        ArrayList<Guests> arl = new ArrayList<Guests>();
        arl.add(new Guests(1, "Matheus Ferreira", 32, 13));

        listaContatos = (ListView)findViewById(R.id.listaContatos);
        adapter = new ListaContatoAdapter(this, arl);
        listaContatos.setAdapter(adapter);
    }
}
