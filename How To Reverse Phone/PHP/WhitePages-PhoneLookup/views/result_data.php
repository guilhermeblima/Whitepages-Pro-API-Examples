<?php
if (isset($result_data)) {
    if (!empty($result_data)) {
        foreach ($result_data as $key => $value) { ?>
            <div class="detail_wrapper">
                <?php include 'phone_result.php'; ?>
            </div>
        <?php }
    } else { ?>
        <div class="error_box">
            No result found.
        </div>
    <?php }
}
