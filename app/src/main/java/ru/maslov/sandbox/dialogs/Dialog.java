package ru.maslov.sandbox.dialogs;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;

import ru.maslov.sandbox.R;


/**
 * Created by Администратор on 28.02.2016.
 */
public class Dialog extends DialogFragment {

    private DialogInterface.OnClickListener mItemClickListener;
    private DialogInterface.OnMultiChoiceClickListener mMultiChoiceClickListener;
    private DialogInterface.OnClickListener mPositiveButtonClickListener;
    private DialogInterface.OnClickListener mNegativeButtonClickListener;
    private IEditTextClick mEditTextClick;

    private BaseAdapter customListAdapter;
    private View customView;

    public final static String TYPE = "type";
    public static final String EDIT_TEXT_TYPE = "edit_text_type";
    public static final String CAPTION = "caption";
    public final static String ITEMS = "items";
    public final static String MULTICHOICE_ITEMS = "multichoice_items";
    public static final String MESSAGE = "message";
    public static final String EDIT_TEXT_DEFAULT_TEXT = "default_text";
    public static final String CUSTOM_VIEW_RES_ID = "custom_view_id";
    public static final String CHECKED_ITEMS = "checked_items";

    public static final int DIALOG_SINGLECHOICE = 100;
    public static final int DIALOG_MESSAGE = 101;
    public static final int DIALOG_CONFIRM = 102;
    public static final int DIALOG_EDIT_TEXT = 103;
    public static final int DIALOG_CUSTOM_VIEW = 104;
    public static final int DIALOG_CUSTOM_LIST = 105;
    public static final int DIALOG_VIOLATION_COMMENTS = 106;
    public static final int DIALOG_MULTICHOICE = 107;

    public void setEditTextClick(IEditTextClick mEditTextClick) {
        this.mEditTextClick = mEditTextClick;
    }

    public void setCustomListAdapter(BaseAdapter adapter){
        customListAdapter = adapter;
    }

    public void setItemClickListener(DialogInterface.OnClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public void setMultiChoiceClickListener(DialogInterface.OnMultiChoiceClickListener mMultiChoiceClickListener) {
        this.mMultiChoiceClickListener = mMultiChoiceClickListener;
    }

    public void setPositiveButtonClickListener(DialogInterface.OnClickListener mPositiveButtonClickListener) {
        this.mPositiveButtonClickListener = mPositiveButtonClickListener;
    }

    public void setNegativeButtonClickListener(DialogInterface.OnClickListener mNegativeButtonClickListener) {
        this.mNegativeButtonClickListener = mNegativeButtonClickListener;
    }

    public void setCustomView(View customView) {
        this.customView = customView;
    }

    public Dialog() {
    }

    public static Dialog newInstance(Bundle args){
        Dialog dialog = new Dialog();
        dialog.setArguments(args);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
        String title = getArguments().getString(CAPTION);
        if (title != null) {
            builder.setTitle(title);
        }
        if (getArguments().getString(MESSAGE) != null){
            builder.setMessage(getArguments().getString(MESSAGE));
        }
        switch(getArguments().getInt(TYPE)){
            case DIALOG_SINGLECHOICE:{
                builder.setItems(getArguments().getCharSequenceArray(ITEMS), mItemClickListener);
                break;
            }
            case DIALOG_MESSAGE:{
                builder.setPositiveButton(getString(R.string.btn_ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                break;
            }
            case DIALOG_CONFIRM:{
                builder.setPositiveButton(getString(R.string.btn_ok), mPositiveButtonClickListener);
                builder.setNegativeButton(getString(R.string.btn_cancel), mNegativeButtonClickListener);
                builder.setIcon(getResources().getDrawable(R.drawable.ic_warning_black_48dp));
                break;
            }
            case DIALOG_EDIT_TEXT:{
                String hint = getArguments().getString(EDIT_TEXT_DEFAULT_TEXT);
                final EditText input = new EditText(getActivity());
                input.setText(hint);
                input.setInputType(getArguments().getInt(EDIT_TEXT_TYPE));
                builder.setView(input);
                builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            mEditTextClick.onClick(input.getText().toString());
                            dialog.dismiss();
                    }
                });
                break;
            }
            case DIALOG_CUSTOM_VIEW:{
                builder.setView(customView);
                builder.setPositiveButton(getString(R.string.btn_ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                break;
            }
            case DIALOG_CUSTOM_LIST:{
                int resId = getArguments().getInt(CUSTOM_VIEW_RES_ID);
                View customView = LayoutInflater.from(getActivity()).inflate(resId, null);
                ListView list = (ListView) customView.findViewById(android.R.id.list);
                list.setAdapter(customListAdapter);
                builder.setView(customView);
                builder.setPositiveButton(getString(R.string.btn_ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                break;
            }
            case DIALOG_VIOLATION_COMMENTS:{

                break;
            }
            case DIALOG_MULTICHOICE:{
                boolean[] checkedItems = getArguments().getBooleanArray(CHECKED_ITEMS);
                CharSequence[] items = getArguments().getCharSequenceArray(MULTICHOICE_ITEMS);
                builder.setMultiChoiceItems(items, checkedItems, mMultiChoiceClickListener);
                builder.setPositiveButton(getString(R.string.btn_ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton(getString(R.string.btn_cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                break;
            }
        }
        return builder.show();
    }
}
