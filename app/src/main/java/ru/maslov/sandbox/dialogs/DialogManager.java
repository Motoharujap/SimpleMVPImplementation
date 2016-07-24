package ru.maslov.sandbox.dialogs;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.BaseAdapter;

import java.util.Calendar;
import java.util.Date;


/**
 * Created by Администратор on 29.02.2016.
 */
public class DialogManager {

    public static Dialog listDialog(String title, int type, CharSequence[] items, DialogInterface.OnClickListener clickListener){
        Bundle args = new Bundle();
        args.putString(Dialog.CAPTION, title);
        args.putInt(Dialog.TYPE, type);
        args.putCharSequenceArray(Dialog.ITEMS, items);
        Dialog dialog = Dialog.newInstance(args);
        dialog.setItemClickListener(clickListener);
        return dialog;
    }

    public static DatePickerDialog datePickerDialog(Context context, DatePickerDialog.OnDateSetListener listener,
                                                    int year, int month, int day){
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, listener, year, month, day);
        return datePickerDialog;
    }

    public static DatePickerDialog datePickerDialog(Context context, DatePickerDialog.OnDateSetListener listener){
        Calendar c = Calendar.getInstance();
        return datePickerDialog(context, listener, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
    }

    public static Dialog multiChoiceListDialog(String title, int type, CharSequence[] items, boolean[] checkedItems, DialogInterface.OnMultiChoiceClickListener multiChoiceClickListener){
        Bundle args = new Bundle();
        args.putString(Dialog.CAPTION, title);
        args.putInt(Dialog.TYPE, type);
        args.putCharSequenceArray(Dialog.MULTICHOICE_ITEMS, items);
        args.putBooleanArray(Dialog.CHECKED_ITEMS, checkedItems);
        Dialog dialog = Dialog.newInstance(args);
        dialog.setMultiChoiceClickListener(multiChoiceClickListener);
        return dialog;
    }
    //TODO rename mb? why passing a type when calling a dialog after one of the types?
    public static Dialog confirmDialog(String title, String message, int type, DialogInterface.OnClickListener positiveClick,
                                       DialogInterface.OnClickListener negativeClick){

        Bundle args = new Bundle();
        args.putString(Dialog.CAPTION, title);
        args.putString(Dialog.MESSAGE, message);
        args.putInt(Dialog.TYPE, type);
        Dialog dialog = Dialog.newInstance(args);
        dialog.setPositiveButtonClickListener(positiveClick);
        if (negativeClick != null) {
            dialog.setNegativeButtonClickListener(negativeClick);
        } else {
            dialog.setNegativeButtonClickListener(new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
        }
        return dialog;
    }

    public static Dialog editTextDialog(String title, String defaultText, int type, int editTextType, IEditTextClick editTextClick){
        Bundle args = new Bundle();
        args.putString(Dialog.CAPTION, title);
        args.putInt(Dialog.TYPE, type);
        args.putInt(Dialog.EDIT_TEXT_TYPE, editTextType);
        args.putString(Dialog.EDIT_TEXT_DEFAULT_TEXT, defaultText);
        Dialog dialog = Dialog.newInstance(args);
        dialog.setEditTextClick(editTextClick);
        return dialog;
    }

    public static Dialog infoDialog(@Nullable String title, String message, int type){
        Bundle args = new Bundle();
        if (title != null) {
            args.putString(Dialog.CAPTION, title);
        }
        args.putString(Dialog.MESSAGE, message);
        args.putInt(Dialog.TYPE, type);
        Dialog dialog = Dialog.newInstance(args);
        return dialog;
    }

    public static Dialog customListDialog(String title, int type, int viewResId, View customView){
        Bundle args = new Bundle();
        args.putString(Dialog.CAPTION, title);
        args.putInt(Dialog.TYPE, type);
        args.putInt(Dialog.CUSTOM_VIEW_RES_ID, viewResId);
        Dialog dialog = Dialog.newInstance(args);
        dialog.setCustomView(customView);
        return dialog;
    }

    public static Dialog customListDialogWithAdapter(String title, int type, int viewResId, BaseAdapter adapter){
        Bundle args = new Bundle();
        args.putString(Dialog.CAPTION, title);
        args.putInt(Dialog.TYPE, type);
        args.putInt(Dialog.CUSTOM_VIEW_RES_ID, viewResId);
        Dialog dialog = Dialog.newInstance(args);
        dialog.setCustomListAdapter(adapter);
        return dialog;
    }
}
