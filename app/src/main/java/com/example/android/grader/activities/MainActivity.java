package com.example.android.grader.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.android.grader.R;
import com.example.android.grader.adapter.AssignmentAdapter;
import com.example.android.grader.adapter.SubmissionAdapter;
import com.example.android.grader.databinding.ActivityMainBinding;
import com.example.android.grader.fragments.AssignmentDetailFragment;
import com.example.android.grader.fragments.AssignmentListFragment;
import com.example.android.grader.fragments.CreateFragment;
import com.example.android.grader.fragments.SubmissionDetailFragment;
import com.example.android.grader.models.Assignment;
import com.example.android.grader.models.Submission;

public class MainActivity extends AppCompatActivity implements AssignmentAdapter.AssignmentClickHandler, SubmissionAdapter.SubmissionClickHandler, AssignmentListFragment.HandleNewAssignmentClick, CreateFragment.HandleSaveAssignment {
    private static final String CREATE_FRAGMENT = "create_fragment";
    private AssignmentListFragment assignmentListFragment;
    private static final String ASSIGNMENT_LIST_FRAGMENT = "assignment_list_fragment";
    private static final String ASSIGNMENT_DETAIL_FRAGMENT = "assignment_detail_fragment";
    private static final String SUBMISSION_DETAIL_FRAGMENT = "submission_detail_fragment";
    private ActivityMainBinding binding;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        assignmentListFragment = (AssignmentListFragment) fragmentManager.findFragmentByTag(ASSIGNMENT_LIST_FRAGMENT);

        if (assignmentListFragment == null) {
            assignmentListFragment = AssignmentListFragment.newInstance();
            fragmentManager
                    .beginTransaction()
                    .add(R.id.fragmentContainer, assignmentListFragment, ASSIGNMENT_LIST_FRAGMENT)
                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public void onAssignmentClick(Assignment assignment) {
        AssignmentDetailFragment assignmentDetailFragment = AssignmentDetailFragment.newInstance(assignment);
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, assignmentDetailFragment, ASSIGNMENT_DETAIL_FRAGMENT)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onSubmissionClick(Submission submission, String assignmentTitle) {
        SubmissionDetailFragment submissionDetailFragment = SubmissionDetailFragment.newInstance(submission, assignmentTitle);
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, submissionDetailFragment, SUBMISSION_DETAIL_FRAGMENT)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onNewAssignmentClick() {
        CreateFragment createFragment = CreateFragment.newInstance();
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, createFragment, CREATE_FRAGMENT)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onSaveAssignment(Assignment assignment) {
        AssignmentListFragment assignmentListFragment = AssignmentListFragment.newInstance(assignment);
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, assignmentListFragment, ASSIGNMENT_LIST_FRAGMENT)
                .addToBackStack(null)
                .commit();
    }
}