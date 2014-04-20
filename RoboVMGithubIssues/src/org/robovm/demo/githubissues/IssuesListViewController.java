package org.robovm.demo.githubissues;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHIssueState;
import org.kohsuke.github.GHRepository;
import org.robovm.apple.foundation.NSArray;
import org.robovm.apple.foundation.NSIndexPath;
import org.robovm.apple.foundation.NSString;
import org.robovm.apple.uikit.UIScrollView;
import org.robovm.apple.uikit.UITableView;
import org.robovm.apple.uikit.UITableViewCell;
import org.robovm.apple.uikit.UITableViewCellAccessoryType;
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
	protected void dispose(boolean finalizing) {
		super.dispose(finalizing);
		System.out.println("DISPOSE IssuesListViewController");
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
		int row = (int) indexPath.getIndexAt(1);
		GHIssue issue = getIssues().get(row);
		cell.getTextLabel().setText(issue.getTitle());
		System.out.println(issue.getTitle());
		return cell;
	}
	
	// RoboVM quirks
	
	@Override
	public void didScroll(UIScrollView scrollView) {
		System.out.println("You scrolled");
	}
	
	@Override
	public NSArray<NSString> getSectionIndexTitles(UITableView tableView) {
		System.out.println("Index titles");
		return null;
	}
	
	@Override
	public double getEstimatedRowHeight(UITableView tableView,
			NSIndexPath indexPath) {
		return 44.0;
	}
	
	@Override
	public UITableViewCellAccessoryType getRowAccessoryType(
			UITableView tableView, NSIndexPath indexPath) {
		return UITableViewCellAccessoryType.None;
	}
	
	@Override
	public boolean canEditRow(UITableView tableView, NSIndexPath indexPath) {
		return false;
	}
	
	@Override
	public void willDisplayCell(UITableView tableView, UITableViewCell cell,
			NSIndexPath indexPath) {
	}
	
	@Override
	public NSIndexPath willSelectRow(UITableView tableView,
			NSIndexPath indexPath) {
		// TODO Auto-generated method stub
		return indexPath;
	}

}
