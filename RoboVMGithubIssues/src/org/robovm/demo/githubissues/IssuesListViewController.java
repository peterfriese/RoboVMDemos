package org.robovm.demo.githubissues;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHIssueState;
import org.kohsuke.github.GHRepository;
import org.robovm.apple.foundation.NSIndexPath;
import org.robovm.apple.uikit.NSIndexPathExtensions;
import org.robovm.apple.uikit.UITableView;
import org.robovm.apple.uikit.UITableViewCell;
import org.robovm.apple.uikit.UITableViewController;
import org.robovm.objc.ObjCClass;

public class IssuesListViewController extends UITableViewController {

	private static final String CELL_IDENTIFIER = "CellIdentifier";
	private GHRepository repository;

	public void setRepository(GHRepository repository) {
		this.repository = repository;
	}
	
	public GHRepository getRepository() {
		return repository;
	}
	
	private List<GHIssue> issues;

	private List<GHIssue> getIssues() {
		if (issues == null) {
			issues = new ArrayList<GHIssue>();
			
			try {
				if (repository.hasIssues()) {
					List<GHIssue> allOpenIssues = this.repository.getIssues(GHIssueState.OPEN);
					issues.addAll(allOpenIssues);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return issues;
	}
	
	public IssuesListViewController() {
		super();
		this.setTitle("Issues");
	}

	@Override
	public void viewDidLoad() {
		super.viewDidLoad();
		
		this.getTableView().registerReusableCellClass(ObjCClass.getByType(UITableViewCell.class), CELL_IDENTIFIER);
	}
	
	@Override
	public long getNumberOfRowsInSection(UITableView tableView, long section) {
		return getIssues().size();
	}
	
	@Override
	public UITableViewCell getRowCell(UITableView tableView, NSIndexPath indexPath) {
		UITableViewCell cell = (UITableViewCell) tableView.dequeueReusableCell(CELL_IDENTIFIER);
		int row = (int) NSIndexPathExtensions.getRow(indexPath);
		GHIssue issue = getIssues().get(row);
		cell.getTextLabel().setText(issue.getTitle());
		System.out.println(issue.getTitle());
		return cell;
	}
}
