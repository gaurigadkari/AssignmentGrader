package com.example.android.grader.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.android.grader.R;
import com.example.android.grader.adapter.AssignmentAdapter;
import com.example.android.grader.databinding.FragmentAssignmentListBinding;
import com.example.android.grader.models.Assignment;
import com.example.android.grader.network.RetrofitClient;
import com.example.android.grader.utils.EndlessRecyclerViewScrollListener;
import com.example.android.grader.utils.Utilities;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AssignmentListFragment extends Fragment {

    private FragmentAssignmentListBinding binding;
    private RecyclerView assignmentsRecyclerView;
    private AssignmentAdapter adapter;
    private ArrayList<Assignment> assignments;
    private EndlessRecyclerViewScrollListener scrollListener;
    private SwipeRefreshLayout swipeContainer;
    LinearLayoutManager linearLayoutManager;
    private ProgressBar progressBar;

    public static AssignmentListFragment newInstance() {
        AssignmentListFragment assignmentListFragment = new AssignmentListFragment();
        return assignmentListFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Indicating the fragment will populate its own menu, onCreateOptionsMenu of fragment
        setHasOptionsMenu(true);
        // Indicating the fragment will retain itself on config changes even if activity is destroyed
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Verifying if a saved instance exists before inflating the fragment
        if (savedInstanceState == null) {
            // Inflate the layout for this fragment
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_assignment_list, container, false);
        }
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Verifying if a saved instance exists before intializing views and class level variables
        if (savedInstanceState == null) {
            assignmentsRecyclerView = binding.rvAssignmentList;
            progressBar = binding.progressBar;

            linearLayoutManager = new LinearLayoutManager(getActivity());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            assignmentsRecyclerView.setLayoutManager(linearLayoutManager);

            //  assignmentsRecyclerView.addOnScrollListener(scrollListener);
            swipeContainer = binding.swipeContainer;
            // Setup refresh listener which triggers new data loading
            swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    assignments.clear();
                    adapter.notifyDataSetChanged();
                    // Make sure you call swipeContainer.setRefreshing(false)
                    // once the network request has completed successfully.
                    retroNetworkCall("", 0);
                }
            });

            //Verifying if the phone has connectivity before making a network call
            if (Utilities.isNetworkAvailable(getActivity()) && Utilities.isOnline()) {
                retroNetworkCall("", 0);
            } else {
                Snackbar.make(assignmentsRecyclerView, R.string.device_offline, Snackbar.LENGTH_LONG).show();
            }
        }
        //((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        assignments = new ArrayList<Assignment>();
        adapter = new AssignmentAdapter(getActivity(), assignments);
        assignmentsRecyclerView.setAdapter(adapter);

        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                //loadNextDataFromApi(page);
            }
        };
    }

    private void retroNetworkCall(String s, int i) {
        // RetrofitClient is a singleton
        Call<List<Assignment>> call = RetrofitClient.getInstance().getApiInterface().getAssignmentResults(RetrofitClient.ACCESS_TOKEN_VALUE);

        showProgressBar();
        // Asynchronous network call using call.enqueue will make a call to the callback passed as a parameter
        call.enqueue(new Callback<List<Assignment>>() {
            @Override
            public void onResponse(Call<List<Assignment>> call, Response<List<Assignment>> response) {
                hideProgressBar();
                if (response.code() == RetrofitClient.ERROR_CODE_TOO_MANY_REQUESTS) {
                    Snackbar.make(assignmentsRecyclerView, R.string.error_429, Snackbar.LENGTH_LONG).show();
                } else if (response.body() != null) {
                    List<Assignment> responseAssignments = response.body();
                    if (responseAssignments.size() == 0) {
                        Snackbar.make(assignmentsRecyclerView, R.string.no_results, Snackbar.LENGTH_LONG).show();
                    } else if (responseAssignments.size() != 0) {
                        assignments.addAll(responseAssignments);
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Assignment>> call, Throwable t) {
                hideProgressBar();
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }
}
