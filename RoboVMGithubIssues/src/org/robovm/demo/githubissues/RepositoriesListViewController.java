package org.robovm.demo.githubissues;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.kohsuke.github.GHMyself;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.robovm.apple.foundation.NSIndexPath;
import org.robovm.apple.uikit.NSIndexPathExtensions;
import org.robovm.apple.uikit.UITableView;
import org.robovm.apple.uikit.UITableViewCell;
import org.robovm.apple.uikit.UITableViewController;
import org.robovm.objc.ObjCClass;

public class RepositoriesListViewController extends UITableViewController {

	private static final String CELL_IDENTIFIER = "CellIdentifier";
	
	private List<GHRepository> repositories;

	private List<GHRepository> getRepositories() {
		if (repositories == null) {
			repositories = new ArrayList<GHRepository>();
			try {
				GitHub github = GitHub.connectUsingPassword("username",
						"xxx");
				GHMyself myself = github.getMyself();
				System.out.println(String.format("User %s just signed in",
						myself));
				Map<String, GHRepository> allRepositories = myself
						.getAllRepositories();
				repositories.addAll(allRepositories.values());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return repositories;
	}
	
	public RepositoriesListViewController() {
		super();
		this.setTitle("Repositories");
	}
	
	@Override
	public void viewDidLoad() {
		super.viewDidLoad();
		this.getTableView().registerReusableCellClass(ObjCClass.getByType(UITableViewCell.class), CELL_IDENTIFIER);
	}
	
	@Override
	public void didSelectRow(UITableView tableView, NSIndexPath indexPath) {
		int row = (int) NSIndexPathExtensions.getRow(indexPath);
		System.out.println(String.format("Selected %s", row));
		GHRepository repository = getRepositories().get(row);
		
		IssuesListViewController issuesListViewController = new IssuesListViewController();
		issuesListViewController.setRepository(repository);
		getNavigationController().addStrongRef(issuesListViewController);
		getNavigationController().pushViewController(issuesListViewController, true);
	}
	
	@Override
	public long getNumberOfRowsInSection(UITableView tableView, long section) {
		return getRepositories().size();
	}
	
	@Override
	public UITableViewCell getRowCell(UITableView tableView,
			NSIndexPath indexPath) {
		UITableViewCell cell = (UITableViewCell) tableView.dequeueReusableCell(CELL_IDENTIFIER);
		int row = (int) indexPath.getIndexAt(1);
		GHRepository repository = getRepositories().get(row);
		cell.getTextLabel().setText(repository.getName());
		return cell;
	}
}
