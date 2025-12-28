package com.example.a65131433_bigproject.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a65131433_bigproject.R;

import java.util.ArrayList;
import java.util.List;

public class FunctionCardAdapter extends RecyclerView.Adapter<FunctionCardAdapter.FunctionViewHolder> {
    private List<FunctionItem> functionList;
    private OnFunctionClickListener listener;

    public interface OnFunctionClickListener {
        void onFunctionClick(FunctionItem item);
    }

    public FunctionCardAdapter(OnFunctionClickListener listener) {
        this.listener = listener;
        this.functionList = new ArrayList<>();
        initializeFunctions();
    }

    private void initializeFunctions() {
        functionList.add(new FunctionItem("Đăng ký khám bệnh", R.drawable.ic_medical, R.drawable.card_function_dangkykham, "btnDangKyKham"));
        functionList.add(new FunctionItem("Bệnh án", R.drawable.ic_file_medical, R.drawable.card_function_benhan, "btnBenhAn"));
        functionList.add(new FunctionItem("Hóa đơn", R.drawable.ic_receipt, R.drawable.card_function_hoadon, "btnHoaDon"));
        functionList.add(new FunctionItem("Toa thuốc hiện tại", R.drawable.ic_prescription, R.drawable.card_function_toathuoc, "btnToaThuocHienTai"));
        functionList.add(new FunctionItem("Điểm tích lũy", R.drawable.ic_star, R.drawable.card_function_diemtichluy, "btnDiemTichLuy"));
        functionList.add(new FunctionItem("Danh sách bác sĩ", R.drawable.ic_doctor, R.drawable.card_function_bacsi, "btnDanhSachBacSi"));
    }

    @NonNull
    @Override
    public FunctionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_function_card, parent, false);
        return new FunctionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FunctionViewHolder holder, int position) {
        FunctionItem item = functionList.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return functionList.size();
    }

    class FunctionViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private LinearLayout linearLayoutContent;
        private ImageView ivIcon;
        private TextView tvFunctionName;

        public FunctionViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            linearLayoutContent = itemView.findViewById(R.id.linearLayoutContent);
            ivIcon = itemView.findViewById(R.id.ivIcon);
            tvFunctionName = itemView.findViewById(R.id.tvFunctionName);
        }

        public void bind(FunctionItem item) {
            tvFunctionName.setText(item.getName());
            ivIcon.setImageResource(item.getIconRes());
            
            // Set background cho LinearLayout
            if (linearLayoutContent != null) {
                linearLayoutContent.setBackgroundResource(item.getBackgroundRes());
            }

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onFunctionClick(item);
                }
            });
        }
    }

    public static class FunctionItem {
        private String name;
        private int iconRes;
        private int backgroundRes;
        private String buttonId;

        public FunctionItem(String name, int iconRes, int backgroundRes, String buttonId) {
            this.name = name;
            this.iconRes = iconRes;
            this.backgroundRes = backgroundRes;
            this.buttonId = buttonId;
        }

        public String getName() {
            return name;
        }

        public int getIconRes() {
            return iconRes;
        }

        public int getBackgroundRes() {
            return backgroundRes;
        }

        public String getButtonId() {
            return buttonId;
        }
    }
}

