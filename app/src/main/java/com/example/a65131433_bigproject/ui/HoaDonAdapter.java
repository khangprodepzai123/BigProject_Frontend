package com.example.a65131433_bigproject.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a65131433_bigproject.R;
import com.example.a65131433_bigproject.models.HoaDonResponse;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.HoaDonViewHolder> {
    private List<HoaDonResponse.HoaDonData> hoaDonList;
    private NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

    public HoaDonAdapter(List<HoaDonResponse.HoaDonData> hoaDonList) {
        this.hoaDonList = hoaDonList;
    }

    public void updateData(List<HoaDonResponse.HoaDonData> newList) {
        this.hoaDonList = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HoaDonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_hoa_don, parent, false);
        return new HoaDonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HoaDonViewHolder holder, int position) {
        HoaDonResponse.HoaDonData hoaDon = hoaDonList.get(position);
        holder.bind(hoaDon, currencyFormat);
    }

    @Override
    public int getItemCount() {
        return hoaDonList != null ? hoaDonList.size() : 0;
    }

    static class HoaDonViewHolder extends RecyclerView.ViewHolder {
        private TextView tvMaHd, tvNgayLap, tvThanhTien, tvBacSi;

        public HoaDonViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaHd = itemView.findViewById(R.id.tvMaHd);
            tvNgayLap = itemView.findViewById(R.id.tvNgayLap);
            tvThanhTien = itemView.findViewById(R.id.tvThanhTien);
            tvBacSi = itemView.findViewById(R.id.tvBacSi);
        }

        public void bind(HoaDonResponse.HoaDonData hoaDon, NumberFormat currencyFormat) {
            tvMaHd.setText("Mã hóa đơn: " + hoaDon.getMaHd());
            tvNgayLap.setText("Ngày lập: " + (hoaDon.getNgayLap() != null ? hoaDon.getNgayLap() : "N/A"));
            tvBacSi.setText("Bác sĩ: " + (hoaDon.getBacSi() != null ? hoaDon.getBacSi() : "N/A"));
            
            BigDecimal thanhTien = hoaDon.getThanhTien();
            if (thanhTien != null) {
                tvThanhTien.setText("Thành tiền: " + formatCurrency(thanhTien));
            } else {
                tvThanhTien.setText("Thành tiền: N/A");
            }
        }

        private String formatCurrency(BigDecimal amount) {
            return String.format(Locale.getDefault(), "%,.0f", amount.doubleValue()) + " đ";
        }
    }
}

