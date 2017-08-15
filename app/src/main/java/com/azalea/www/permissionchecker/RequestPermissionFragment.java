package com.azalea.www.permissionchecker;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmccmap.permissionchecker.PermissionRequestor;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RequestPermissionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class RequestPermissionFragment extends Fragment {

	public static final int REQUEST_IN_FRAGMENT = 101;

	private OnFragmentInteractionListener mListener;

	public RequestPermissionFragment() {
		// Required empty public constructor
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_request_permission, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		view.findViewById(R.id.btn_request_inact).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mListener.OnRequestPermission(new String[]{Manifest.permission.READ_CONTACTS}, 9);
			}
		});

		view.findViewById(R.id.btn_request_infrag).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				PermissionRequestor.reqeustPermissionInFrag(RequestPermissionFragment.this, new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_IN_FRAGMENT);
			}
		});
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		if (context instanceof OnFragmentInteractionListener) {
			mListener = (OnFragmentInteractionListener) context;
		} else {
			throw new RuntimeException(context.toString()
					+ " must implement OnFragmentInteractionListener");
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

		switch (requestCode){
			case REQUEST_IN_FRAGMENT:
				mListener.OnPrintLog("授权完成");
				break;
		}

		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
	}

	/**
	 * This interface must be implemented by activities that contain this
	 * fragment to allow an interaction in this fragment to be communicated
	 * to the activity and potentially other fragments contained in that
	 * activity.
	 * <p>
	 * See the Android Training lesson <a href=
	 * "http://developer.android.com/training/basics/fragments/communicating.html"
	 * >Communicating with Other Fragments</a> for more information.
	 */
	public interface OnFragmentInteractionListener {
		// TODO: Update argument type and name
		void OnRequestPermission(String[] permission, int requestCode);
		void OnPrintLog(String msg);
	}
}
