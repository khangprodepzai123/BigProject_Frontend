package com.example.a65131433_bigproject.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a65131433_bigproject.R;
import com.example.a65131433_bigproject.models.ToaThuocHienTaiResponse;

import java.util.ArrayList;
import java.util.List;

public class ToaThuocHienTaiAdapter extends RecyclerView.Adapter<ToaThuocHienTaiAdapter.ThuocViewHolder> {
    private List<ToaThuocHienTaiResponse.ToaThuocHienTaiData.ThuocData> thuocList;

    public ToaThuocHienTaiAdapter(List<ToaThuocHienTaiResponse.ToaThuocHienTaiData.ThuocData> thuocList) {
        this.thuocList = thuocList != null ? thuocList : new ArrayList<>();
    }

    @NonNull
    @Override
    public ThuocViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_toa_thuoc_hien_tai, parent, false);
        return new ThuocViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThuocViewHolder holder, int position) {
        ToaThuocHienTaiResponse.ToaThuocHienTaiData.ThuocData thuoc = thuocList.get(position);
        holder.bind(thuoc);
    }

    @Override
    public int getItemCount() {
        return thuocList.size();
    }

    public void updateList(List<ToaThuocHienTaiResponse.ToaThuocHienTaiData.ThuocData> newList) {
        this.thuocList = newList != null ? newList : new ArrayList<>();
        notifyDataSetChanged();
    }

    static class ThuocViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTenThuoc, tvSoLuong, tvLieuDung, tvCachDung, tvSoLanUong;

        public ThuocViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenThuoc = itemView.findViewById(R.id.tvTenThuoc);
            tvSoLuong = itemView.findViewById(R.id.tvSoLuong);
            tvLieuDung = itemView.findViewById(R.id.tvLieuDung);
            tvCachDung = itemView.findViewById(R.id.tvCachDung);
            tvSoLanUong = itemView.findViewById(R.id.tvSoLanUong);
        }

        public void bind(ToaThuocHienTaiResponse.ToaThuocHienTaiData.ThuocData thuoc) {
            tvTenThuoc.setText(thuoc.getTenThuoc() != null ? thuoc.getTenThuoc() : "N/A");
            tvSoLuong.setText("Số lượng: " + thuoc.getSoLuong() + " viên");
            tvLieuDung.setText("Liều dùng: " + (thuoc.getLieuDung() != null ? thuoc.getLieuDung() : "N/A"));
            tvCachDung.setText("Cách dùng: " + (thuoc.getCachDung() != null ? thuoc.getCachDung() : "N/A"));
            tvSoLanUong.setText("Số lần uống: " + thuoc.getSoLanUongMoiNgay() + " lần/ngày");
        }
    }
}



