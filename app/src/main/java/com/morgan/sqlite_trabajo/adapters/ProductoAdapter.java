package com.morgan.sqlite_trabajo.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.morgan.sqlite.R;
import com.morgan.sqlite.models.Producto;

import java.util.ArrayList;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ViewHoldder> {

    ArrayList<Producto> listaProductos;

    public ProductoAdapter(ArrayList<Producto> productos) {
        this.listaProductos = productos;
    }

    @NonNull
    @Override
    public ProductoAdapter.ViewHoldder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto, parent, false);
        ViewHoldder vHolder = new ViewHoldder(view);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoAdapter.ViewHoldder holder, int position) {
        //Context context = holder.itemView.getContext();
        Producto producto = listaProductos.get(position);
        holder.cargar(producto);

    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public class ViewHoldder extends RecyclerView.ViewHolder {

        ImageView imgCategoria;
        TextView txtNombreProducto, txtPPrecio, txtPStock;

        public ViewHoldder(@NonNull View itemView) {
            super(itemView);

            imgCategoria = itemView.findViewById(R.id.imgCategoria);
            txtNombreProducto = itemView.findViewById(R.id.txtNombreProducto);
            txtPPrecio = itemView.findViewById(R.id.txtPPrecio);
            txtPStock = itemView.findViewById(R.id.txtPStock);

        }

        public void cargar(Producto producto) {
            txtNombreProducto.setText(producto.getNombre());
            txtPPrecio.setText(producto.getPrecio());
            txtPStock.setText(producto.getStock());

            if (producto.getCategoria().equals("Computación")){
                imgCategoria.setImageResource(R.drawable.computador);
            }
            if (producto.getCategoria().equals("Vestuario Mujer")){
                imgCategoria.setImageResource(R.drawable.vestuariomujer);
            }
            if (producto.getCategoria().equals("Vestuario Hombre")){
                imgCategoria.setImageResource(R.drawable.vestuariohombre);
            }
            if (producto.getCategoria().equals("Electrodoméstico")){
                imgCategoria.setImageResource(R.drawable.electrodomesticos);
            }

        }

    }

}