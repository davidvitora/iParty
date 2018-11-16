package com.iparty.Utilities;

import android.content.Context;
import android.widget.TableRow;
import com.iparty.model.Bebida;

public class BebidaRow extends TableRow{
    private Bebida bebida;

    public BebidaRow(Context context, Bebida bebida) {
        super(context);
        this.setBebida(bebida);
    }

    public Bebida getBebida() {
        return bebida;
    }

    public void setBebida(Bebida bebida) {
        this.bebida = bebida;
    }
}
