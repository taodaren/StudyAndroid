package io.github.taodaren.testcardflip;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * 一个呈现在卡片后方的 fragment
 */

public class CardAfterFragment extends Fragment {

    public CardAfterFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card_after, container, false);
        view.findViewById(R.id.img_after).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "反面被点击", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

}
