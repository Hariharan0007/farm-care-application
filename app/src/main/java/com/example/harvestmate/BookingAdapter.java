package com.example.harvestmate;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.BookingHolder> {

    private Context context;
    private ArrayList<BookingModel> BookingModelArrayList;

    // Constructor
    public BookingAdapter(Context context,ArrayList<BookingModel> BookingModelArrayList) {
        this.context = context;
        this.BookingModelArrayList = BookingModelArrayList;
//        System.out.println(this.BookingModelArrayList);
    }

    @NonNull
    @Override
    public BookingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(context).inflate(R.layout.bookings_card_layout, parent, false);
        return new BookingHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingHolder holder, int position) {

        // to set data to textview and imageview of each card layout

        System.out.println("=============Binder is Working==============");

        BookingModel model = BookingModelArrayList.get(position);

        holder.SetDetails(model);


//
////        holder.BookingIcon.setImageResource(R.drawable.farm_care_icon);
//
//        holder.Booking_date.setText(model.getBooking_date());
//
//        holder.Booking_center.setText(model.getBooking_Center());
//
//        holder.Booking_status.setText(model.getApproval_status());
    }

    @Override
    public int getItemCount() {
        // this method is used for showing number of card items in recycler view
        return BookingModelArrayList.size();
    }

    // View holder class for initializing of your views such as TextView and Imageview
    class BookingHolder extends RecyclerView.ViewHolder {
        private final ImageView BookingIcon;
        private final TextView Booking_date;
        private final TextView Booking_status;
        private final TextView Booking_center;

        BookingHolder(View itemView) {
            super(itemView);
            BookingIcon = itemView.findViewById(R.id.card_booking_icon);
            Booking_date = itemView.findViewById(R.id.card_booking_date);
            Booking_center = itemView.findViewById(R.id.card_booking_center);
            Booking_status = itemView.findViewById(R.id.card_booking_approval_status);
        }

        void SetDetails(BookingModel details)
        {
            BookingIcon.setImageResource(R.drawable.farm_care_icon);
            Booking_date.setText(details.getBooking_date());
            Booking_center.setText(details.getBooking_Center());
            Booking_status.setText(details.getApproval_status());
        }
    }
}

