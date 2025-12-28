package com.example.a65131433_bigproject.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a65131433_bigproject.R;
import com.example.a65131433_bigproject.models.BacSiResponse;

import java.util.ArrayList;
import java.util.List;

public class BacSiAdapter extends RecyclerView.Adapter<BacSiAdapter.BacSiViewHolder> {
    private List<BacSiResponse.BacSiData> danhSachBacSi;

    public BacSiAdapter() {
        this.danhSachBacSi = new ArrayList<>();
    }

    @NonNull
    @Override
    public BacSiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_bac_si, parent, false);
        return new BacSiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BacSiViewHolder holder, int position) {
        BacSiResponse.BacSiData bacSi = danhSachBacSi.get(position);
        holder.bind(bacSi);
    }

    @Override
    public int getItemCount() {
        return danhSachBacSi.size();
    }

    public void updateList(List<BacSiResponse.BacSiData> newList) {
        this.danhSachBacSi = newList != null ? newList : new ArrayList<>();
        notifyDataSetChanged();
    }

    static class BacSiViewHolder extends RecyclerView.ViewHolder {
        private TextView tvHoTenBs, tvMaBs, tvTrinhDoHocVan, tvChuyenKhoa, 
                        tvTuoi, tvKinhNghiem, tvChungChiHanhNghe, tvSoLuotKham;

        public BacSiViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHoTenBs = itemView.findViewById(R.id.tvHoTenBs);
            tvMaBs = itemView.findViewById(R.id.tvMaBs);
            tvTrinhDoHocVan = itemView.findViewById(R.id.tvTrinhDoHocVan);
            tvChuyenKhoa = itemView.findViewById(R.id.tvChuyenKhoa);
            tvTuoi = itemView.findViewById(R.id.tvTuoi);
            tvKinhNghiem = itemView.findViewById(R.id.tvKinhNghiem);
            tvChungChiHanhNghe = itemView.findViewById(R.id.tvChungChiHanhNghe);
            tvSoLuotKham = itemView.findViewById(R.id.tvSoLuotKham);
        }

        public void bind(BacSiResponse.BacSiData bacSi) {
            tvHoTenBs.setText(bacSi.getHoTenBs() != null ? bacSi.getHoTenBs() : "N/A");
            tvMaBs.setText("Mã BS: " + (bacSi.getMaBs() != null ? bacSi.getMaBs() : "N/A"));

            // Trình độ học vấn
            if (bacSi.getTrinhDoHocVan() != null && !bacSi.getTrinhDoHocVan().isEmpty()) {
                tvTrinhDoHocVan.setText("Trình độ: " + bacSi.getTrinhDoHocVan());
                tvTrinhDoHocVan.setVisibility(View.VISIBLE);
            } else {
                tvTrinhDoHocVan.setVisibility(View.GONE);
            }

            // Chuyên khoa
            if (bacSi.getChuyenKhoa() != null && !bacSi.getChuyenKhoa().isEmpty()) {
                tvChuyenKhoa.setText("Chuyên khoa: " + bacSi.getChuyenKhoa());
                tvChuyenKhoa.setVisibility(View.VISIBLE);
            } else {
                tvChuyenKhoa.setVisibility(View.GONE);
            }

            // Tuổi
            if (bacSi.getTuoi() > 0) {
                tvTuoi.setText("Tuổi: " + bacSi.getTuoi());
                tvTuoi.setVisibility(View.VISIBLE);
            } else {
                tvTuoi.setVisibility(View.GONE);
            }

            // Kinh nghiệm
            if (bacSi.getKinhNghiem() > 0) {
                tvKinhNghiem.setText("Kinh nghiệm: " + bacSi.getKinhNghiem() + " năm");
                tvKinhNghiem.setVisibility(View.VISIBLE);
            } else {
                tvKinhNghiem.setVisibility(View.GONE);
            }

            // Chứng chỉ hành nghề
            if (bacSi.getChungChiHanhNghe() != null && !bacSi.getChungChiHanhNghe().isEmpty()) {
                tvChungChiHanhNghe.setText("Chứng chỉ: " + bacSi.getChungChiHanhNghe());
                tvChungChiHanhNghe.setVisibility(View.VISIBLE);
            } else {
                tvChungChiHanhNghe.setVisibility(View.GONE);
            }

            // Số lượt khám
            tvSoLuotKham.setText("Đã khám: " + bacSi.getSoLuotKham() + " lượt");
        }
    }
}

