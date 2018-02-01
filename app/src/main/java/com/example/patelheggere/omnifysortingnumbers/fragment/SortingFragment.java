package com.example.patelheggere.omnifysortingnumbers.fragment;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.patelheggere.omnifysortingnumbers.R;
import com.example.patelheggere.omnifysortingnumbers.service.SortingService;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SortingFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SortingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SortingFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View mView;
    private boolean mBound = false;
    private SortingService mService;
    private int[] mMerge = new int[20];
    private int[] mQuick = new int[20];
    private int[] randomNumbers = new int[20];
    private String strMerge = "";
    private String strQuick = "";
    private TextView tvMerge, tvQuick;

    private OnFragmentInteractionListener mListener;

    public SortingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SortingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SortingFragment newInstance(String param1, String param2) {
        SortingFragment fragment = new SortingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            randomNumbers = getArguments().getIntArray("Arrays");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_sorting, container, false);
        tvMerge = mView.findViewById(R.id.tvMerge);
        tvQuick = mView.findViewById(R.id.tvQuick);
        sortService();
        return mView;
    }

    private void sortService()
    {
        Intent intent = new Intent(getContext(), SortingService.class);
        getContext().bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            SortingService.MyBinder mBinder = (SortingService.MyBinder)iBinder;
            mService = mBinder.getService();
            mBound = true;
            strMerge = "Merge Sort \n \t\t";
            strQuick = "Quick Sort \n \t\t";
            mMerge = mService.MergeSort(randomNumbers);
            for (int i = 0; i < mMerge.length; i++)
                strMerge+=mMerge[i]+"\n \t\t";
            mQuick = mService.QuickSort(randomNumbers);
            for (int i = 0; i < mQuick.length; i++)
               strQuick+= mQuick[i]+"\n \t\t";
            tvMerge.setText(strMerge);
            tvQuick.setText(strQuick);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mService = null;
            mBound = false;
        }
    };

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
        mBound = false;
        getContext().unbindService(mServiceConnection);
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
        void onFragmentInteraction(Uri uri);
    }
}
