package com.example.android.grader.fragments;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import android.widget.TextView;

import com.example.android.grader.R;
import com.example.android.grader.adapter.SubmissionAdapter;
import com.example.android.grader.databinding.FragmentAssignmentDetailBinding;
import com.example.android.grader.models.Assignment;
import com.example.android.grader.models.Submission;
import com.example.android.grader.network.RetrofitClient;
import com.example.android.grader.utils.EndlessRecyclerViewScrollListener;
import com.example.android.grader.utils.Utilities;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AssignmentDetailFragment extends Fragment {
    private FragmentAssignmentDetailBinding binding;
    private RecyclerView submissionsRecyclerView;
    private TextView assignmentDetails;
    private SubmissionAdapter adapter;
    private ArrayList<Submission> submissions;
    private SwipeRefreshLayout swipeContainer;
    private static final String ASSIGNMENT = "assignment";
    private Assignment assignment;
    private ProgressBar progressBar;
    private Toolbar toolbar;
    private Context context;

    public AssignmentDetailFragment() {
        // Required empty public constructor
    }

    public static AssignmentDetailFragment newInstance(Assignment assignment) {
        Bundle args = new Bundle();
        AssignmentDetailFragment assignmentDetailFragment = new AssignmentDetailFragment();
        args.putParcelable(ASSIGNMENT, Parcels.wrap(assignment));
        assignmentDetailFragment.setArguments(args);
        return assignmentDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assignment = Parcels.unwrap(getArguments().getParcelable(ASSIGNMENT));
        // Indicating the fragment will retain itself on config changes even if activity is destroyed
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Verifying if a saved instance exists before inflating the fragment
        if (savedInstanceState == null) {
            // Inflate the layout for this fragment
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_assignment_detail, container, false);
        }
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState == null) {
            submissionsRecyclerView = binding.rvSubmissionList;
            assignmentDetails = binding.assignmentDetails;
            toolbar = binding.toolbar;
            submissions = new ArrayList<>();

            progressBar = binding.progressBar;
            ((AppCompatActivity) context).setSupportActionBar(toolbar);
            toolbar.setTitle(assignment.getTitle());
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            submissionsRecyclerView.setLayoutManager(linearLayoutManager);
            adapter = new SubmissionAdapter(assignment.getTitle(), context, submissions);
            submissionsRecyclerView.setAdapter(adapter);

            swipeContainer = binding.swipeContainer;
            // Setup refresh listener which triggers new data loading
            swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    submissions.clear();
                    adapter.notifyDataSetChanged();
                    // Make sure you call swipeContainer.setRefreshing(false)
                    // once the network request has completed successfully.
                    retroNetworkCall(0);
                }
            });

            //Verifying if the phone has connectivity before making a network call
            if (Utilities.isNetworkAvailable(context) && Utilities.isOnline()) {
                retroNetworkCall(0);
            } else {
                Snackbar.make(submissionsRecyclerView, R.string.device_offline, Snackbar.LENGTH_LONG).show();
            }

        } else {
            adapter = new SubmissionAdapter(assignment.getTitle(), context, submissions);
            submissionsRecyclerView.setAdapter(adapter);
        }
        assignmentDetails.setText(assignment.getDescription());
    }

    private void retroNetworkCall(int page) {

        Call<List<Submission>> call = RetrofitClient.getInstance().getApiInterface().getSubmissionResults(assignment.getId(), RetrofitClient.ACCESS_TOKEN_VALUE);
        showProgressBar();
        call.enqueue(new Callback<List<Submission>>() {
            @Override
            public void onResponse(@NonNull Call<List<Submission>> call, @NonNull Response<List<Submission>> response) {
                hideProgressBar();
                if (response.code() == RetrofitClient.ERROR_CODE_TOO_MANY_REQUESTS) {
                    Snackbar.make(submissionsRecyclerView, R.string.error_429, Snackbar.LENGTH_LONG).show();
                } else if (response.body() != null) {

                    List<Submission> responseSubmissions = response.body();
                    if ((responseSubmissions != null ? responseSubmissions.size() : 0) == 0) {
                        Snackbar.make(submissionsRecyclerView, R.string.no_results, Snackbar.LENGTH_LONG).show();
                    } else if (responseSubmissions.size() != 0) {
                        submissions.addAll(responseSubmissions);
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Submission>> call, @NonNull Throwable t) {
                hideProgressBar();
            }
        });
        swipeContainer.setRefreshing(false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.context = null;
    }

    private void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }
}
