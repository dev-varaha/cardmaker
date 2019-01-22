package com.mm.dell.cardmaker2.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.mm.dell.cardmaker2.R;
import com.mm.dell.cardmaker2.Utils.OntextChange;

public class EditTextViewDialog extends Dialog {

    String text;
    Context context;
    OntextChange ontextChange;

    public EditTextViewDialog(Context context, String text, OntextChange ontextChange) {
        super(context);
        this.context = context;
        this.text = text;
        this.ontextChange = ontextChange;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        setContentView(R.layout.edittextview);
        EditText et_entertext = findViewById(R.id.et_enter);
        TextView tv_ok = findViewById(R.id.tv_ok);
        TextView tv_cancel = findViewById(R.id.tv_cancel);
        if (text != null) {
            et_entertext.setText(text);
        }
        tv_ok.setOnClickListener(v -> {
            if (!et_entertext.getText().toString().isEmpty()) {
                ontextChange.onTextChanged(et_entertext.getText().toString());
            }
            this.dismiss();
        });
        tv_cancel.setOnClickListener(v -> {
            this.dismiss();
        });
    }
}
