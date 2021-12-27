package com.jnu.myaccount.ui.item;

import static com.jnu.myaccount.acc.AddActivity.*;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.jnu.myaccount.R;
import com.jnu.myaccount.utils.CalendarUtils;
import com.jnu.myaccount.utils.DataUtils;
import com.jnu.myaccount.utils.KeyBoardUtils;

public class AddExpendFragment extends Fragment implements View.OnClickListener{
    public int selectItem;
    private int num;
    public String selectDate;
    private LinearLayout linearLayout;
    private ImageButton button_foods,button_drinks,button_supplies,button_shopping,button_sports,
            button_call_credit,button_traffic,button_party, button_housing,button_medical,button_gift,button_others;
    private Button button_time, button_remark;
    private TextView addRecord;

    private EditText accountEdit;
    private KeyboardView keyboardView;

    int[] nowDate = new CalendarUtils().getNowDate();
    int[] IntSelectDate = new int[5];

    private void initSelectDate(){
        selectDate = new CalendarUtils().IntToTimeString(nowDate[0],nowDate[1]+1,nowDate[2]);
        IntSelectDate = new CalendarUtils().TimeStringToInt(selectDate,IntSelectDate);
        button_time.setText(IntSelectDate[1]+"月"+IntSelectDate[2]+"日");
    }

    public AddExpendFragment() {

    }

    public static AddExpendFragment newInstance() {
        AddExpendFragment fragment = new AddExpendFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_expend,container,false);

        initView(rootView);
        KeyBoardUtils keyBoardUtils = new KeyBoardUtils(keyboardView,accountEdit);
        keyBoardUtils.showKeyboard();
        initSelectDate();

        keyBoardUtils.setOnEnsureListener(new KeyBoardUtils.OnEnsureListener(){
            @Override
            public void onEnsure() {
                if(accountEdit.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "请输入金额！", Toast.LENGTH_SHORT).show();
                }
                else {
                    DataUtils dataUtils = new DataUtils(getActivity());
                    dataUtils.InsertData(selectItem, Integer.parseInt(accountEdit.getText().toString()), selectDate);
                    getActivity().finish();
                }
            }
        });

        button_remark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        button_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getContext(), AlertDialog.THEME_HOLO_LIGHT,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                selectDate = new CalendarUtils().IntToTimeString(year,month+1,dayOfMonth);
                                IntSelectDate = new CalendarUtils().TimeStringToInt(selectDate,IntSelectDate);
                                button_time.setText(month+1+"月"+dayOfMonth+"日");
                            }
                        },IntSelectDate[0],IntSelectDate[1]-1,IntSelectDate[2]).show();
            }
        });
        return rootView;
    }

    private void initView(View rootView){
        linearLayout = rootView.findViewById(R.id.linear_layout_add_expend);
        addRecord = rootView.findViewById(R.id.text_view_add_expend_record);
        accountEdit = rootView.findViewById(R.id.edit_text_add_expend_num);
        button_time = rootView.findViewById(R.id.button_time);
        keyboardView =rootView.findViewById(R.id.keyBoard);
        button_remark = rootView.findViewById(R.id.button_remark);

        linearLayout = rootView.findViewById(R.id.linear_layout_add_income);
        addRecord = rootView.findViewById(R.id.text_view_add_expend_record);

        button_foods = rootView.findViewById(R.id.button_add_expend_foods);
        button_drinks = rootView.findViewById(R.id.button_add_expend_debt);
        button_supplies = rootView.findViewById(R.id.button_add_expend_supplies);
        button_shopping = rootView.findViewById(R.id.button_add_expend_shopping);
        button_sports = rootView.findViewById(R.id.button_add_expend_sports);
        button_call_credit = rootView.findViewById(R.id.button_add_expend_call_credit);
        button_traffic = rootView.findViewById(R.id.button_add_expend_traffic);
        button_party = rootView.findViewById(R.id.button_add_expend_party);
        button_housing = rootView.findViewById(R.id.button_add_expend_housing);
        button_medical = rootView.findViewById(R.id.button_add_expend_medical);
        button_gift = rootView.findViewById(R.id.button_add_expend_medical);
        button_others = rootView.findViewById(R.id.button_add_expend_medical);
        button_foods.setOnClickListener(this);
        button_drinks.setOnClickListener(this);
        button_supplies.setOnClickListener(this);
        button_shopping.setOnClickListener(this);
        button_sports.setOnClickListener(this);
        button_call_credit.setOnClickListener(this);
        button_traffic.setOnClickListener(this);
        button_party.setOnClickListener(this);
        button_housing.setOnClickListener(this);
        button_medical.setOnClickListener(this);
        button_gift.setOnClickListener(this);
        button_others.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_add_expend_foods:
                linearLayout.setBackgroundColor(getResources().getColor(R.color.foods));
                addRecord.setText(getResources().getString(R.string.foods));
                selectItem = foods;
                break;
            case R.id.button_add_expend_debt:
                linearLayout.setBackgroundColor(getResources().getColor(R.color.debt));
                addRecord.setText(getResources().getString(R.string.debt));
                selectItem = debt;
                break;
            case R.id.button_add_expend_supplies:
                linearLayout.setBackgroundColor(getResources().getColor(R.color.supplies));
                addRecord.setText(getResources().getString(R.string.supplies));
                selectItem = supplies;
                break;
            case R.id.button_add_expend_shopping:
                linearLayout.setBackgroundColor(getResources().getColor(R.color.shopping));
                addRecord.setText(getResources().getString(R.string.shopping));
                selectItem = shopping;
                break;
            case R.id.button_add_expend_sports:
                linearLayout.setBackgroundColor(getResources().getColor(R.color.sports));
                addRecord.setText(getResources().getString(R.string.sports));
                selectItem = sports;
                break;
            case R.id.button_add_expend_call_credit:
                linearLayout.setBackgroundColor(getResources().getColor(R.color.call_credit));
                addRecord.setText(getResources().getString(R.string.call_credit));
                selectItem = call_credit;
                break;
            case R.id.button_add_expend_traffic:
                linearLayout.setBackgroundColor(getResources().getColor(R.color.traffic));
                addRecord.setText(getResources().getString(R.string.traffic));
                selectItem = traffic;
                break;
            case R.id.button_add_expend_party:
                linearLayout.setBackgroundColor(getResources().getColor(R.color.party));
                addRecord.setText(getResources().getString(R.string.party));
                selectItem = party;
                break;
            case R.id.button_add_expend_housing:
                linearLayout.setBackgroundColor(getResources().getColor(R.color.housing));
                addRecord.setText(getResources().getString(R.string.housing));
                selectItem = housing;
                break;
            case R.id.button_add_expend_medical:
                linearLayout.setBackgroundColor(getResources().getColor(R.color.medical));
                addRecord.setText(getResources().getString(R.string.medical));
                selectItem = medical;
                break;
            case R.id.button_add_expend_gift:
                linearLayout.setBackgroundColor(getResources().getColor(R.color.gift));
                addRecord.setText(getResources().getString(R.string.gift));
                selectItem = gift;
                break;
            case R.id.button_add_expend_others:
                linearLayout.setBackgroundColor(getResources().getColor(R.color.others));
                addRecord.setText(getResources().getString(R.string.others));
                selectItem = others;
                break;
        }
    }

}