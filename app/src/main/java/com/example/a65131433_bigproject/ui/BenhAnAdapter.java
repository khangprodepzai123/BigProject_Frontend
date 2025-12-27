package com.example.a65131433_bigproject.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a65131433_bigproject.R;
import com.example.a65131433_bigproject.models.BenhAnResponse;

import java.util.List;

public class BenhAnAdapter extends RecyclerView.Adapter<BenhAnAdapter.BenhAnViewHolder> {
    private List<BenhAnResponse.BenhAnData> benhAnList;

    public BenhAnAdapter(List<BenhAnResponse.BenhAnData> benhAnList) {
        this.benhAnList = benhAnList;
    }

    public void updateData(List<BenhAnResponse.BenhAnData> newList) {
        this.benhAnList = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BenhAnViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_benh_an, parent, false);
        return new BenhAnViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BenhAnViewHolder holder, int position) {
        BenhAnResponse.BenhAnData benhAn = benhAnList.get(position);
        holder.bind(benhAn);
    }

    @Override
    public int getItemCount() {
        return benhAnList != null ? benhAnList.size() : 0;
    }

    static class BenhAnViewHolder extends RecyclerView.ViewHolder {
        private TextView tvMaBenhAn, tvNgayKham, tvBacSi, tvChuanDoan, tvLyDoKham;

        public BenhAnViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaBenhAn = itemView.findViewById(R.id.tvMaBenhAn);
            tvNgayKham = itemView.findViewById(R.id.tvNgayKham);
            tvBacSi = itemView.findViewById(R.id.tvBacSi);
            tvChuanDoan = itemView.findViewById(R.id.tvChuanDoan);
            tvLyDoKham = itemView.findViewById(R.id.tvLyDoKham);
        }

        public void bind(BenhAnResponse.BenhAnData benhAn) {
            tvMaBenhAn.setText("Mã bệnh án: " + benhAn.getMaBenhAn());
            tvNgayKham.setText("Ngày khám: " + (benhAn.getNgayKham() != null ? benhAn.getNgayKham() : "N/A"));
            tvBacSi.setText("Bác sĩ: " + (benhAn.getBacSi() != null ? benhAn.getBacSi() : "N/A"));
            tvChuanDoan.setText("Chuẩn đoán: " + (benhAn.getChuanDoan() != null ? benhAn.getChuanDoan() : "N/A"));
            tvLyDoKham.setText("Lý do khám: " + (benhAn.getLyDoKham() != null ? benhAn.getLyDoKham() : "N/A"));
        }
    }
}

