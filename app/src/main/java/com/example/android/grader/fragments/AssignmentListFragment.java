package com.example.android.grader.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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
import com.raizlabs.android.dbflow.sql.language.SQLite;

import org.parceler.Parcels;

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
    private SwipeRefreshLayout swipeContainer;
    private LinearLayoutManager linearLayoutManager;
    private ProgressBar progressBar;
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private HandleNewAssignmentClick listener;
    private static final String ASSIGNMENT = "assignment";

    public static AssignmentListFragment newInstance() {
        return new AssignmentListFragment();
    }

    public static AssignmentListFragment newInstance(Assignment assignment) {
        Bundle args = new Bundle();
        AssignmentListFragment assignmentListFragment = new AssignmentListFragment();
        args.putParcelable(ASSIGNMENT, Parcels.wrap(assignment));
        assignmentListFragment.setArguments(args);
        return assignmentListFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        // Verifying if a saved instance exists before initializing views and class level variables

        if (savedInstanceState == null) {
            assignmentsRecyclerView = binding.rvAssignmentList;
            progressBar = binding.progressBar;
            toolbar = binding.toolbar;
            linearLayoutManager = new LinearLayoutManager(getActivity());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            assignmentsRecyclerView.setLayoutManager(linearLayoutManager);
            assignments = new ArrayList<>();
            fab = binding.fab;
            adapter = new AssignmentAdapter(getActivity(), assignments);
            assignmentsRecyclerView.setAdapter(adapter);
            swipeContainer = binding.swipeContainer;
            // Setup refresh listener which triggers new data loading
            swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    assignments.clear();
                    adapter.notifyDataSetChanged();
                    // Make sure you call swipeContainer.setRefreshing(false)
                    // once the network request has completed successfully.
                    retroNetworkCall(0);
                }
            });

            //Verifying if the phone has connectivity before making a network call
            if (Utilities.isNetworkAvailable(getActivity()) && Utilities.isOnline()) {
                retroNetworkCall(0);
            } else {
                Snackbar.make(assignmentsRecyclerView, R.string.device_offline, Snackbar.LENGTH_LONG).show();
            }

        } else {
            adapter = new AssignmentAdapter(getActivity(), assignments);
            assignmentsRecyclerView.setAdapter(adapter);
        }
        listener = (HandleNewAssignmentClick) getActivity();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onNewAssignmentClick();
            }
        });
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle("Assignments");
    }

    private void retroNetworkCall(int page) {
        ArrayList<Assignment> assignmentArrayList = (ArrayList<Assignment>) SQLite.select().
                from(Assignment.class).queryList();
        assignments.addAll(assignmentArrayList);
        adapter.notifyDataSetChanged();
        // RetrofitClient is a singleton
        Call<List<Assignment>> call = RetrofitClient.getInstance().getApiInterface().getAssignmentResults(RetrofitClient.ACCESS_TOKEN_VALUE);
        showProgressBar();
        // Asynchronous network call using call.enqueue will make a call to the callback passed as a parameter
        call.enqueue(new Callback<List<Assignment>>() {
            @Override
            public void onResponse(@NonNull Call<List<Assignment>> call, @NonNull Response<List<Assignment>> response) {
                hideProgressBar();
                if (response.code() == RetrofitClient.ERROR_CODE_TOO_MANY_REQUESTS) {
                    Snackbar.make(assignmentsRecyclerView, R.string.error_429, Snackbar.LENGTH_LONG).show();
                } else if (response.body() != null) {
                    List<Assignment> responseAssignments = response.body();
                    if ((responseAssignments != null ? responseAssignments.size() : 0) == 0) {
                        Snackbar.make(assignmentsRecyclerView, R.string.no_results, Snackbar.LENGTH_LONG).show();
                    } else if (responseAssignments.size() != 0) {
                        assignments.addAll(responseAssignments);
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Assignment>> call, @NonNull Throwable t) {
                hideProgressBar();
            }
        });
    }

    private void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    public interface HandleNewAssignmentClick {
        void onNewAssignmentClick();
    }
}
