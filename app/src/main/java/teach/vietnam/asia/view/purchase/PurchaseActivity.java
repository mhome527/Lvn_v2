package teach.vietnam.asia.view.purchase;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.crash.FirebaseCrash;

import java.util.ArrayList;
import java.util.List;

import teach.vietnam.asia.BuildConfig;
import teach.vietnam.asia.Constant;
import teach.vietnam.asia.R;
import teach.vietnam.asia.entity.Purchase;
import teach.vietnam.asia.utils.IabHelper;
import teach.vietnam.asia.utils.IabResult;
import teach.vietnam.asia.utils.Inventory;
import teach.vietnam.asia.utils.Log;
import teach.vietnam.asia.utils.SkuDetails;
import teach.vietnam.asia.view.base.BaseActivity;

/**
 * This class should be the parent of any Activity that wants to do in app purchases
 * it makes our life easier by wrapping up the talking to the IabHelper and just exposing what is needed.
 * <p>
 * When this activity starts it will bind to the Google Play IAB service and check for its availability
 * <p>
 * After that you can purchase items using purchaseItem(String sku) and listening for the result
 * by overriding dealWithPurchaseFailed(IabResult result) and dealWithPurchaseSuccess(IabResult result, Purchase info)
 *
 * @author Blundell
 */
public abstract class PurchaseActivity<T> extends BaseActivity<T> implements IabHelper.OnIabSetupFinishedListener, IabHelper.OnIabPurchaseFinishedListener {
    private String TAG = "PurchaseActivity";

    private IabHelper billingHelper;

    protected abstract void dealWithIabSetupSuccess();

    protected abstract void dealWithIabSetupFailure();

    ///
    public boolean isPurchased = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");

        if (BuildConfig.FLAVOR.equals(Constant.TRIAL_APP)) {

            billingHelper = new IabHelper(this, Constant.BASE_64_KEY);
            billingHelper.startSetup(this);
        } else {
            isPurchased = true;
        }

        if (!BuildConfig.DEBUG)
            FirebaseCrash.logcat(Log.INFO, TAG, "onCreate");

        super.onCreate(savedInstanceState);
    }

    @Override
    public void onIabSetupFinished(IabResult result) {
        if (result.isSuccess()) {
            Log.d(TAG, "In-app Billing set up: " + result);
            dealWithIabSetupSuccess();
        } else {
            Log.e(TAG, "onIabSetupFinished Problem setting up In-app Billing: " + result);
            dealWithIabSetupFailure();
        }
    }

    protected boolean getItemPurchased() {
        Log.i(TAG, "getItemPurchased ...");
//        if (!BuildConfig.DEBUG)
//            FirebaseCrash.logcat(Log.INFO, TAG, "getItemPurchased");
        if (billingHelper == null)
            return false;

        List<String> list = new ArrayList<>();
        list.add(Constant.SKU);
        try {
            Inventory inv = billingHelper.queryInventory(true, list);
            if (inv != null) {
                if (inv.hasPurchase(Constant.SKU)) {
                    SkuDetails detail = inv.getSkuDetails(Constant.SKU);
                    if (detail != null) {
                        Log.i(TAG, "getItemPurchased price:" + detail.getPrice());
                        return Constant.ITEM_PURCHASED;
                    } else {
                        Log.e(TAG, "getItemPurchased detail null");
                        return !Constant.ITEM_PURCHASED;
                    }
                } else {
                    Log.i(TAG, "getItemPurchased item not found");
                }
            } else {
                Log.e(TAG, "getItemPurchased query null");
            }
        } catch (Exception e) {
            Log.trace(e);
        }
        return !Constant.ITEM_PURCHASED;
    }

    public void clearPurchaseTest() {
        List<String> list = new ArrayList<>();
        list.add(Constant.SKU);
        try {
            Log.i(TAG, "clearPurchaseTest...");
            Inventory inv = billingHelper.queryInventory(true, list);
            billingHelper.consumeAsync(inv.getPurchase("android.test.purchased"), null);
        } catch (Exception e) {
            Log.trace(e);
        }
    }

    public void purchaseItem() {
        try {
            if (billingHelper != null) {
                billingHelper.flagEndAsync();
                billingHelper.launchPurchaseFlow(this, Constant.SKU, Constant.PURCHASE_REQUEST_CODE, this);
            }
        } catch (Exception e) {
            if (BuildConfig.DEBUG) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, "purchaseItem error: " + e.getMessage());
            } else
                Toast.makeText(getApplicationContext(), R.string.msg_error_purchase, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == Constant.PURCHASE_REQUEST_CODE) {
            if (billingHelper != null)
                billingHelper.handleActivityResult(requestCode, resultCode, data);
        } else {
            Log.e(TAG, "onActivityResult Fail!!!!!!!!!!!");
        }
    }

    /**
     * Security Recommendation: When you receive the purchase response from Google Play, make sure to check the returned data
     * signature, the orderId, and the developerPayload string in the Purchase object to make sure that you are getting the
     * expected values. You should verify that the orderId is a unique value that you have not previously processed, and the
     * developerPayload string matches the token that you sent previously with the purchase request. As a further security
     * precaution, you should perform the verification on your own secure server.
     */
    @Override
    public void onIabPurchaseFinished(IabResult result, Purchase info) {
        Log.i(TAG, "onIabPurchaseFinished");
        if (result.isFailure()) {
            dealWithPurchaseFailed(result);
        } else if (Constant.SKU.equals(info.getSku())) {
            dealWithPurchaseSuccess(result, info);
        }
//        finish();
    }

    protected void dealWithPurchaseFailed(IabResult result) {
        Log.i(TAG, "Error purchasing: " + result);
    }

    protected void dealWithPurchaseSuccess(IabResult result, Purchase info) {
        Log.i(TAG, "====== Item purchased: " + result);
//        dealWithPurchaseSuccess(result, info);
        dealWithIabSetupSuccess();
        // DEBUG XXX
        // We consume the item straight away so we can test multiple purchases
//        billingHelper.consumeAsync(info, null);
        // END DEBUG
    }

    @Override
    protected void onDestroy() {
        disposeBillingHelper();
        super.onDestroy();
    }

    private void disposeBillingHelper() {
        if (billingHelper != null) {
            billingHelper.dispose();
        }
        billingHelper = null;
    }
}
