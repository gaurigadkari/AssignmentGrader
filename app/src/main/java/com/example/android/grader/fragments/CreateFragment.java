package com.example.android.grader.fragments;

import android.app.DatePickerDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.android.grader.R;
import com.example.android.grader.databinding.FragmentCreateBinding;
import com.example.android.grader.models.Assignment;
import com.example.android.grader.utils.Utilities;

import java.util.Calendar;

public class CreateFragment extends Fragment {
    private FragmentCreateBinding binding;
    private EditText assignmentTitle, assignmentDescription, dueAt;
    private CheckBox chkLockPastDueDate;
    private Button btnCreateAssignment;
    private DatePickerDialog.OnDateSetListener date;
    private HandleSaveAssignment listener;

    public CreateFragment() {
        // Required empty public constructor
    }


    public static CreateFragment newInstance() {
        return new CreateFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Verifying if a saved instance exists before inflating the fragment
        if (savedInstanceState == null) {
            // Inflate the layout for this fragment
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create, container, false);
        }
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        assignmentTitle = binding.etAssignmentTitle;
        assignmentDescription = binding.etAssignmentDetails;
        dueAt = binding.etDueDate;
        chkLockPastDueDate = binding.checkboxLockPastDueDate;
        btnCreateAssignment = binding.btnCreate;

        listener = (HandleSaveAssignment) getActivity();

        //tweets.clear();


        //open date picker on dueAt click
        dueAt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), date, year, month, day);
                //datePickerDialog.getDatePicker().setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                datePickerDialog.show();
            }
        });

        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month,
                                  int day) {
                dueAt.setText((month + 1) + "/" + day + "/" + year);
            }

        };

        btnCreateAssignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Assignment newAssignment = new Assignment();
                newAssignment.setTitle(assignmentTitle.getText().toString());
                newAssignment.setDescription(assignmentDescription.getText().toString());
                newAssignment.setDueAt(Utilities.revertDateFormat(dueAt.getText().toString(), "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));
                newAssignment.setLockAfterDue(chkLockPastDueDate.isSelected());
                newAssignment.save();
                listener.onSaveAssignment(newAssignment);
            }
        });
    }

    public interface HandleSaveAssignment {
        void onSaveAssignment(Assignment assignment);
    }
}
