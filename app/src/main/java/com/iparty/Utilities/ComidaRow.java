package com.iparty.Utilities;

import android.content.Context;
import android.widget.TableRow;

import com.iparty.model.Bebida;
import com.iparty.model.Comida;

public class ComidaRow extends TableRow{
    private Comida comida;

    public ComidaRow(Context context, Comida comida) {
        super(context);
        this.setComida(comida);
    }

    public Comida getComida() {
        return comida;
    }

    public void setComida(Comida comida) {
        this.comida = comida;
    }
}
