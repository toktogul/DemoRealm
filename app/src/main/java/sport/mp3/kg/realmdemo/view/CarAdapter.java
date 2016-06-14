package sport.mp3.kg.realmdemo.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sport.mp3.kg.realmdemo.R;
import sport.mp3.kg.realmdemo.models.BaseModel;

/**
 * Created by chen on 13.06.16.
 */
public class CarAdapter<M extends BaseModel> extends RecyclerView.Adapter<CarAdapter.CarViewHolder> {


    private static final String TAG = CarAdapter.class.getName();
    private List<M> carList = new ArrayList<>();
    private OnCarAdapterListener listener;

    public CarAdapter(OnCarAdapterListener listener) {
        this.listener = listener;
    }

    @Override
    public CarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);

        final CarViewHolder holder = new CarViewHolder(v);

        holder.carDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if(position != RecyclerView.NO_POSITION){
                    listener.onItemDelete(carList.get(position), position);
                }
            }
        });


        holder.carEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if(position != RecyclerView.NO_POSITION){
                    listener.onItemEdit(carList.get(position), position);
                }
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if(position != RecyclerView.NO_POSITION){
                    listener.onClickItem(carList.get(position));
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(CarViewHolder holder, int position) {
        holder.setData(carList.get(position).getName(), position);
    }

    public void setData(List<M> list){
        carList = list;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return carList.size();
    }

    public void putItem(M car) {
        carList.add(car);
        notifyItemInserted(carList.size());
    }

    public void removeItem(int position) {
        carList.remove(position);
        notifyItemRemoved(position);
    }

    public void updateItem(M s, int position) {
        carList.set(position, s);
        notifyItemChanged(position);
    }

    public static class CarViewHolder extends RecyclerView.ViewHolder{

        private final TextView carName;
        private final TextView carPosition;
        private final ImageView carDeleteButton;
        private final ImageView carEditButton;
        private final View view;

        public CarViewHolder(View itemView) {
            super(itemView);
            carName = (TextView) itemView.findViewById(R.id.item_car_name);
            carPosition = (TextView) itemView.findViewById(R.id.item_car_position);
            carDeleteButton = (ImageView) itemView.findViewById(R.id.item_delete);
            carEditButton = (ImageView) itemView.findViewById(R.id.item_edit);
            view = (View) itemView.findViewById(R.id.item_view);
        }


        public void setData(String car, int position) {
            carName.setText(car);
            carPosition.setText(position+1 + "");
        }
    }


    public interface OnCarAdapterListener<T>{
        void onItemDelete(T car, int position);
        void onItemEdit(T car, int position);
        void onClickItem(T car);
    }
}
