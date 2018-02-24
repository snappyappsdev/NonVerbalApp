package com.snappyappsdev.nonverbalapp.communicator;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.snappyappsdev.nonverbalapp.R;
import com.snappyappsdev.nonverbalapp.database.model.Pec;

import java.text.NumberFormat;

import javax.inject.Inject;
import javax.inject.Provider;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.neverstoplearning.poweradapter.item.ItemRenderer;

/**
 * Created by lrocha on 2/18/18.
 */

class PecRenderer implements ItemRenderer<Pec> {
    private final Provider<CommunicatorPresenter> presenterProvider;

    @Inject
    public PecRenderer(Provider<CommunicatorPresenter> presenterProvider) {
        this.presenterProvider = presenterProvider;
    }

    @Override
    public int layoutRes() {
        return R.layout.list_item_pec;
    }

    @Override
    public View createView(@NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutRes(), parent, false);
        view.setTag(new ViewBinder(view, presenterProvider.get()));
        return view;
    }


    @Override
    public void render(@NonNull View itemView, @NonNull Pec item) {
        ((ViewBinder) itemView.getTag()).bind(item);
    }

    static class ViewBinder {

        @BindView(R.id.pec_holder_title) TextView pecNameText;

        private Pec pec;

        ViewBinder(View itemView, CommunicatorPresenter presenter) {
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> {
                if (pec != null) {
                    presenter.onPecClicked(pec);
                }
            });
        }

        void bind(Pec pec) {
            this.pec = pec;
            pecNameText.setText(pec.getTitle());
        }
    }
}
