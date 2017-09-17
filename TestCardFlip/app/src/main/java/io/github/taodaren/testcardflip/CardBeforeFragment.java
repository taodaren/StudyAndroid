package io.github.taodaren.testcardflip;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * 一个呈现在卡片前方的 fragment
 */

public class CardBeforeFragment extends Fragment {

    public CardBeforeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card_before, container, false);
        view.findViewById(R.id.ll_before).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "正面被点击", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

}
