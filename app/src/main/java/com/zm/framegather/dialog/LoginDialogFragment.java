package com.zm.framegather.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.zm.framegather.R;

public class LoginDialogFragment extends DialogFragment {
	private EditText mUsername;
	private EditText mPassword;

	public interface LoginInputListener {
		void onLoginInputComplete(String username, String password);
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		// Get the layout inflater
		LayoutInflater inflater = getActivity().getLayoutInflater();
		View view = inflater.inflate(R.layout.fragment_login_dialog, null);
		mUsername = (EditText) view.findViewById(R.id.id_txt_username);
		mPassword = (EditText) view.findViewById(R.id.id_txt_password);
		// Inflate and set the layout for the dialog
		// Pass null as the parent view because its going in the dialog layout
		builder.setView(view)
				// Add action buttons
				.setPositiveButton("Sign in",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int id) {
								if(getActivity() instanceof LoginInputListener){
									LoginInputListener listener = (LoginInputListener) getActivity();
									listener.onLoginInputComplete(mUsername
											.getText().toString(), mPassword
											.getText().toString());
								}else if(getParentFragment() instanceof LoginInputListener){
									LoginInputListener listener = (LoginInputListener) getParentFragment();
									listener.onLoginInputComplete(mUsername
											.getText().toString(), mPassword
											.getText().toString());
								}

							}
						}).setNegativeButton("Cancel", null);
		return builder.create();
	}
}
