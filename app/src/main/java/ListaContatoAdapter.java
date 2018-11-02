import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.iparty.R;
import com.iparty.model.Guests;

import java.util.ArrayList;
import java.util.List;

public class ListaContatoAdapter extends BaseAdapter {

    private Activity activity;
    private List<Guests> lst;
    private LayoutInflater inflater;

    public ListaContatoAdapter(Activity activity, ArrayList<Guests> lst) {
        this.activity = activity;
        this.lst = lst;
    }

    @Override
    public int getCount() {
        return this.lst.size();
    }

    @Override
    public Object getItem(int i) {
        return this.lst.get(i);
    }

    @Override
    public long getItemId(int i) { return this.lst.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (inflater == null) {
            inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if (view == null) {
            view = inflater.inflate(R.layout.list_row, viewGroup, false);
        }

        TextView nome = (TextView)view.findViewById(R.id.nome);
        nome.setText(lst.get(i).getNome());

        TextView idade = (TextView)view.findViewById(R.id.idade);
        idade.setText(lst.get(i).getIdade());

        TextView cpf = (TextView)view.findViewById(R.id.telefone);
        cpf.setText(lst.get(i).getTelefone());

        return view;
    }
}
